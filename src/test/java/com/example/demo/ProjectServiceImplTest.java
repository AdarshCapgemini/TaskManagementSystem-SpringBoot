package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.ProjectUserDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.exception.ProjectAlreadyExistsException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.ProjectListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectServiceImpl;

class ProjectServiceImplTest {

	@Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() throws ProjectListIsEmptyException {
        Project project1 = new Project();
        Project project2 = new Project();
        List<Project> projects = Arrays.asList(project1, project2);

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testCreateProject() throws ProjectAlreadyExistsException, UserDoesNotExistException {
        ProjectUserDto projectUserDto = new ProjectUserDto();
        projectUserDto.setProjectId(1);
        projectUserDto.setProjectName("Test Project");
        projectUserDto.setDescription("Test Description");
        projectUserDto.setStartDate(LocalDate.now());
        projectUserDto.setEndDate(LocalDate.now().plusDays(10));
        projectUserDto.setUserId(1);

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));

        User user = new User();
        user.setUserId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.empty());
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project result = projectService.createProject(projectUserDto);

        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
        verify(projectRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(1);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void testGetProjectByProjectId() throws ProjectDoesNotExistException {
        Project project = new Project();
        project.setProjectId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(projectRepository.getProjectByProjectId(1)).thenReturn(new ProjectUserDto());

        ProjectUserDto result = projectService.getProjectByProjectId(1);

        assertNotNull(result);
        verify(projectRepository, times(1)).findById(1);
        verify(projectRepository, times(1)).getProjectByProjectId(1);
    }
    
    @Test
    void testGetAllProjectsByUserId()throws ProjectDoesNotExistException{
        List<ProjectUserDto> projects = Arrays.asList(new ProjectUserDto(), new ProjectUserDto());

        when(projectRepository.getAllProjectsByUserId()).thenReturn(projects);

        List<ProjectUserDto> result = projectService.getAllProjectsByUserId();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).getAllProjectsByUserId();
    }

    @Test
    void testGetProjectByUserId() throws ProjectListIsEmptyException {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        // Mock the findAll method to return a non-empty list
        when(projectRepository.findAll()).thenReturn(projects);
        when(projectRepository.getProjectByUserId(1)).thenReturn(projects);

        List<Project> result = projectService.getProjectByUserId(1);

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
        verify(projectRepository, times(1)).getProjectByUserId(1);
    }


    @Test
    void testGetCurrentlyOngoingProjects() throws ProjectListIsEmptyException {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        // Mock the findAll method to return a non-empty list
        when(projectRepository.findAll()).thenReturn(projects);
        when(projectRepository.findCurrentlyOngoingProjects()).thenReturn(projects);

        List<Project> result = projectService.getCurrentlyOngoingProjects();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
        verify(projectRepository, times(1)).findCurrentlyOngoingProjects();
    }


    @Test
    void testGetProjectsByStatus() {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.getProjectsByStatus("active")).thenReturn(projects);

        List<Project> result = projectService.getProjectsByStatus("active");

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).getProjectsByStatus("active");
    }

    @Test
    void testGetProjectsWithHighPriorityTasks() throws ProjectListIsEmptyException {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        when(projectRepository.getProjectsWithHighPriorityTasks()).thenReturn(projects);

        List<Project> result = projectService.getProjectsWithHighPriorityTasks();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).getProjectsWithHighPriorityTasks();
    }

    @Test
    void testGetProjectsInSpecificDateRange() throws ProjectListIsEmptyException {
        List<Project> projects = Arrays.asList(new Project(), new Project());

        // Mock the findAll method to return a non-empty list
        when(projectRepository.findAll()).thenReturn(projects);
        when(projectRepository.getProjectsInSpecificDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(projects);

        List<Project> result = projectService.getProjectsInSpecificDateRange(LocalDate.now(), LocalDate.now().plusDays(10));

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
        verify(projectRepository, times(1)).getProjectsInSpecificDateRange(any(LocalDate.class), any(LocalDate.class));
    }


    @Test
    void testUpdateProjectByProjectId() throws ProjectDoesNotExistException, UserDoesNotExistException {
        ProjectUserDto projectUserDto = new ProjectUserDto();
        projectUserDto.setProjectName("Updated Project");
        projectUserDto.setDescription("Updated Description");
        projectUserDto.setStartDate(LocalDate.now());
        projectUserDto.setEndDate(LocalDate.now().plusDays(10));
        projectUserDto.setUserId(1);

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Updated Project");
        project.setDescription("Updated Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));

        User user = new User();
        user.setUserId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project result = projectService.updateProjectByProjectId(1, projectUserDto);

        assertNotNull(result);
        assertEquals("Updated Project", result.getProjectName());
        verify(projectRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(1);
        verify(projectRepository, times(1)).save(any(Project.class));
    }

}
