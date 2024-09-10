package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskProjectUserDto;
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

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	/**
     * Creates a new task with the provided details.
     * @param taskProjectUserDto Data transfer object containing task details including project and user information.
     * @return The created Task entity.
     * @throws TaskAlreadyExistsException If a task with the same ID already exists.
     * @throws ProjectDoesNotExistException If the project associated with the task does not exist.
     * @throws UserDoesNotExistException If the user assigned to the task does not exist.
     */
	@Override
	public Task createTask(TaskProjectUserDto taskProjectUserDto)
			throws TaskAlreadyExistsException, ProjectDoesNotExistException, UserDoesNotExistException {
		Task task = new Task();
		task.setTaskId(taskProjectUserDto.getTaskId());
		task.setTaskName(taskProjectUserDto.getTaskName());
		task.setDescription(taskProjectUserDto.getDescription());
		task.setDueDate(taskProjectUserDto.getDueDate());
		task.setPriority(taskProjectUserDto.getPriority());
		task.setStatus(taskProjectUserDto.getStatus());

		if (!taskRepository.findById(task.getTaskId()).isEmpty()) {
			throw new TaskAlreadyExistsException("Task already exist");
		} else {
			Project project = projectRepository.findById(taskProjectUserDto.getProjectId())
					.orElseThrow(() -> new ProjectDoesNotExistException("Project doesn't exist"));
			User user = userRepository.findById(taskProjectUserDto.getUserId())
					.orElseThrow(() -> new UserDoesNotExistException("User doesn't exist"));
			task.setProject(project);
			task.setUser(user);

			return taskRepository.save(task);
		}
	}

	/**
     * Retrieves all tasks.
     * @return A list of all Task entities.
     * @throws TaskListIsEmptyException If no tasks are found.
     */
	@Override
	public List<Task> getAllTasks() throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.findAll();
		}
	}
	
	/**
     * Retrieves a task by its ID.
     * @param taskId The ID of the task to retrieve.
     * @return The Task entity with the specified ID.
     * @throws TaskDoesntExistException If no task with the given ID is found.
     */
	@Override
	public Task getTasksByTaskId(int taskId)throws TaskDoesntExistException{
		if (taskRepository.findById(taskId).isEmpty()) {
			throw new TaskDoesntExistException("Task Doesn't exists");
		} else {
			return taskRepository.getTasksByTaskId(taskId);
		}
	}

	/**
     * Retrieves all overdue tasks.
     * @return A list of overdue Task entities.
     * @throws TaskListIsEmptyException If no overdue tasks are found.
     */
	@Override
	public List<Task> getOverdueTasks() throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getOverdueTasks();
		}

	}
	
	/**
     * Retrieves tasks based on their status.
     * @param status The status of the tasks to retrieve.
     * @return A list of Task entities with the specified status.
     * @throws TaskListIsEmptyException If no tasks with the given status are found.
     */
	@Override
	public List<Task> getTaskBystatus(String status) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTaskBystatus(status);
		}

	}

	/**
     * Retrieves tasks filtered by priority and status.
     * @param priority The priority of the tasks to retrieve.
     * @return A list of Task entities with the specified priority.
     * @throws TaskListIsEmptyException If no tasks with the specified priority are found.
     */
	@Override
	public List<Task> getTasksByPriorityAndStatus(String priority) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksByPriorityAndStatus(priority);
		}
	}

	/**
     * Retrieves tasks that are due soon.
     * @return A list of tasks that are approaching their due date.
     * @throws TaskListIsEmptyException If no tasks are found that are due soon.
     */
	@Override
	public List<Task> getTasksDueSoon() throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksDueSoon();
		}
	}
	
	/**
     * Retrieves tasks assigned to a specific user.
     * @param userId The ID of the user whose tasks are to be retrieved.
     * @return A list of Task entities assigned to the specified user.
     * @throws TaskListIsEmptyException If no tasks are found for the user.
     */
	@Override
	public List<Task> getTasksByUserId(int userId) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksByUserId(userId);
		}
	}

	/**
     * Retrieves tasks assigned to a specific user with a specific status.
     * @param userId The ID of the user whose tasks are to be retrieved.
     * @param status The status of the tasks to retrieve.
     * @return A list of Task entities assigned to the user with the specified status.
     * @throws TaskListIsEmptyException If no tasks are found for the user with the specified status.
     */
	@Override
	public List<Task> getTasksByUserAndStatus(String userId, String status) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksByUserAndStatus(userId, status);
		}
	}
	
	/**
     * Retrieves tasks associated with a specific project.
     * @param projectId The ID of the project whose tasks are to be retrieved.
     * @return A list of Task entities associated with the specified project.
     * @throws TaskListIsEmptyException If no tasks are found for the project.
     */
	@Override
	public List<Task> getTasksByProject(int  projectId) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksByProject(projectId);
		}
	}
	
	/**
     * Retrieves the count of tasks associated with a specific project.
     * @param projectId The ID of the project for which the task count is to be retrieved.
     * @return The number of tasks associated with the specified project.
     * @throws TaskListIsEmptyException If no tasks are found for the project.
     */
	@Override
	public int getTaskCountOfProject(int  projectId) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTaskCountOfProject(projectId);
		}
	}

	/**
     * Retrieves tasks associated with a specific category.
     * @param categoryId The ID of the category whose tasks are to be retrieved.
     * @return A list of Task entities associated with the specified category.
     * @throws TaskListIsEmptyException If no tasks are found for the category.
     */
	@Override
	public List<Task> getTasksByCategory(String categoryId) throws TaskListIsEmptyException {
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getTasksByCategory(categoryId);
		}
	}

	/**
     * Updates details of an existing task.
     * @param taskId The ID of the task to update.
     * @param taskProjectUserDto Data transfer object containing updated task details.
     * @return The updated Task entity.
     * @throws TaskDoesntExistException If no task with the given ID is found.
     * @throws ProjectDoesNotExistException If the project associated with the task does not exist.
     * @throws UserDoesNotExistException If the user assigned to the task does not exist.
     */
	@Override
	public Task updateTaskDetailsById(int taskId, TaskProjectUserDto taskProjectUserDto) throws TaskDoesntExistException, ProjectDoesNotExistException, UserDoesNotExistException {
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskName(taskProjectUserDto.getTaskName());
		task.setDescription(taskProjectUserDto.getDescription());
		task.setDueDate(taskProjectUserDto.getDueDate());
		task.setPriority(taskProjectUserDto.getPriority());
		task.setStatus(taskProjectUserDto.getStatus());

		if (!taskRepository.findById(taskId).isPresent()) {
			throw new TaskDoesntExistException("Task doesn't exist");
		} else {
			Project project = projectRepository.findById(taskProjectUserDto.getProjectId())
					.orElseThrow(() -> new ProjectDoesNotExistException("Project doesn't exist"));
			User user = userRepository.findById(taskProjectUserDto.getUserId())
					.orElseThrow(() -> new UserDoesNotExistException("User doesn't exist"));
			task.setProject(project);
			task.setUser(user);

			return taskRepository.save(task);
		}
	}

	/**
     * Deletes a task by its ID.
     * @param taskId The ID of the task to delete.
     * @throws TaskDoesntExistException If no task with the given ID is found.
     */
	@Override
	public void deleteTaskById(int taskId) throws TaskDoesntExistException {
		if (taskRepository.findById(taskId).isEmpty()) {
			throw new TaskDoesntExistException("Task doesn't exist exist");
		} else {
			Task task = taskRepository.findById(taskId).get();
//			taskRepository.deleteById(taskId);
			taskRepository.deleteByTaskId(taskId);

		}

	}
	
	/**
     * Retrieves all tasks associated with a specific category.
     * @param categoryId The ID of the category whose tasks are to be retrieved.
     * @return A list of tasks associated with the specified category.
     * @throws CategoryDoesntExistException If the category with the specified ID does not exist.
     * @throws TaskListIsEmptyException If no tasks are found for the category.
     */
	public List<Object[]> getAllTaskForCategory(int categoryId) throws CategoryDoesntExistException,TaskListIsEmptyException {
		if(taskRepository.findAll().isEmpty())
			throw new TaskListIsEmptyException("Task list is empty");
		else if(categoryRepository.findById(categoryId).isEmpty())
			throw new CategoryDoesntExistException("Category doesn't exist");
		else
		{
			List<Object[]> allTaskForCategory = taskRepository.getAllTaskForCategory(categoryId);
			return allTaskForCategory;
		}
	}
	
	/**
     * Associates a task with one or more categories.
     * @param taskId The ID of the task to associate.
     * @param categoryIds A list of category IDs to associate with the task.
     */
	@Transactional
    public void associateTaskWithCategory(int taskId,List<Integer> categoryId) {
        for (int category : categoryId) {
            taskRepository.associateTaskWithCategory(taskId, category);
        }
    }
	
	/**
     * Retrieves all task IDs.
     * @return An array of all task IDs.
     * @throws TaskListIsEmptyException If no tasks are found.
     */
	@Override
	public int[] getAllTaskIds() throws TaskListIsEmptyException{
		if (taskRepository.findAll().isEmpty()) {
			throw new TaskListIsEmptyException("Task list is Empty");
		} else {
			return taskRepository.getAllTaskIds();
		}
		
	}

}
