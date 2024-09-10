package com.example.demo;
 
import static org.junit.jupiter.api.Assertions.*;
 
import static org.mockito.Mockito.*;
 
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.example.demo.dto.TaskProjectUserDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.TaskAlreadyExistsException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.TaskListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TaskServiceImpl;
 
@SpringBootTest
public class TaskServiceImplTest {
 
    @Mock
    private TaskRepository taskRepository;
 
    @Mock
    private ProjectRepository projectRepository;
 
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private CategoryRepository categoryRepository;
 
    @InjectMocks
    private TaskServiceImpl taskService;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testCreateTask() throws TaskAlreadyExistsException, ProjectDoesNotExistException, UserDoesNotExistException {
        TaskProjectUserDto dto = new TaskProjectUserDto();
        dto.setTaskId(1);
        dto.setTaskName("Sample Task");
        dto.setDescription("This is a sample task description.");
        dto.setDueDate(LocalDate.now().plusDays(1));
        dto.setPriority("Medium");
        dto.setStatus("Pending");
        dto.setProjectId(1);
        dto.setUserId(1);
 
        when(taskRepository.findById(dto.getTaskId())).thenReturn(Optional.empty());
        when(projectRepository.findById(dto.getProjectId())).thenReturn(Optional.of(new Project()));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(new User()));
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());
 
        Task createdTask = taskService.createTask(dto);
 
        assertNotNull(createdTask);
        verify(taskRepository, times(1)).save(any(Task.class));
    }
 
    @Test
    public void testGetAllTasks() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
 
        List<Task> tasks = taskService.getAllTasks();
 
        assertFalse(tasks.isEmpty());
        assertEquals(2, tasks.size());
    }
 
    @Test
    public void testGetOverdueTasks() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        when(taskRepository.getOverdueTasks()).thenReturn(Arrays.asList(new Task()));
 
        List<Task> tasks = taskService.getOverdueTasks();
 
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
 
    @Test
    public void testGetTasksByPriorityAndStatus() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        when(taskRepository.getTasksByPriorityAndStatus("Medium")).thenReturn(Arrays.asList(new Task()));
 
        List<Task> tasks = taskService.getTasksByPriorityAndStatus("Medium");
 
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
 
    @Test
    public void testGetTasksDueSoon() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        when(taskRepository.getTasksDueSoon()).thenReturn(Arrays.asList(new Task()));
 
        List<Task> tasks = taskService.getTasksDueSoon();
 
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
 
    @Test
    public void testGetTasksByUserAndStatus() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        when(taskRepository.getTasksByUserAndStatus("1", "Pending")).thenReturn(Arrays.asList(new Task()));
 
        List<Task> tasks = taskService.getTasksByUserAndStatus("1", "Pending");
 
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
 
    @Test
    public void testGetTasksByCategory() throws TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        when(taskRepository.getTasksByCategory("1")).thenReturn(Arrays.asList(new Task()));
 
        List<Task> tasks = taskService.getTasksByCategory("1");
 
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
    }
 
    
    @Test
    void testUpdateTaskDetailsById_TaskDoesNotExist() {
        TaskProjectUserDto dto = new TaskProjectUserDto();
        when(taskRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(TaskDoesntExistException.class, () -> {
            taskService.updateTaskDetailsById(1, dto);
        });
    }
 
    @Test
    void testUpdateTaskDetailsById_ProjectDoesNotExist() {
        TaskProjectUserDto dto = new TaskProjectUserDto();
        dto.setProjectId(1);
 
        when(taskRepository.findById(1)).thenReturn(Optional.of(new Task()));
        when(projectRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(ProjectDoesNotExistException.class, () -> {
            taskService.updateTaskDetailsById(1, dto);
        });
    }
 
    @Test
    void testUpdateTaskDetailsById_UserDoesNotExist() {
        TaskProjectUserDto dto = new TaskProjectUserDto();
        dto.setProjectId(1);
        dto.setUserId(1);
 
        when(taskRepository.findById(1)).thenReturn(Optional.of(new Task()));
        when(projectRepository.findById(1)).thenReturn(Optional.of(new Project()));
        when(userRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(UserDoesNotExistException.class, () -> {
            taskService.updateTaskDetailsById(1, dto);
        });
    }
 
 
    @Test
    public void testGetAllTaskForCategory() throws CategoryDoesntExistException, TaskListIsEmptyException {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));
        
        Category mockCategory = new Category();
        when(categoryRepository.findById(1)).thenReturn(Optional.of(mockCategory));
        
        when(taskRepository.getAllTaskForCategory(1)).thenReturn(Arrays.asList(new Object[]{1, "Task1"}, new Object[]{2, "Task2"}));
 
        List<Object[]> tasks = taskService.getAllTaskForCategory(1);
 
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }
 
    @Test
    public void testAssociateTaskWithCategory() {
        doNothing().when(taskRepository).associateTaskWithCategory(anyInt(), anyInt());
 
        taskService.associateTaskWithCategory(1, Arrays.asList(1, 2, 3));
 
        verify(taskRepository, times(3)).associateTaskWithCategory(eq(1), anyInt());
    }
}