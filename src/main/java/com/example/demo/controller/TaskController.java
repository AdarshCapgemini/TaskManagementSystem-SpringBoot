package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.dto.TaskProjectUserDto;
import com.example.demo.entity.Task;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.TaskAlreadyExistsException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.TaskListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("http://localhost:4200")
public class TaskController {

	@Autowired
	TaskService taskService;

	/**
	 * Handles HTTP POST requests to create a new Task.
	 * @param taskProjectUserDto
	 * @return ResponseEntity containing a SuccessResponse and a HTTP status code(201)
	 * @throws TaskAlreadyExistsException
	 * @throws ProjectDoesNotExistException
	 * @throws UserDoesNotExistException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createTask(@RequestBody @Valid TaskProjectUserDto taskProjectUserDto) throws TaskAlreadyExistsException, ProjectDoesNotExistException, UserDoesNotExistException {
		
		taskService.createTask(taskProjectUserDto);
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS", "Task added successfully");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Handles HTTP GET requests to fetch all available Tasks.
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200s)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Task>> getAllTasks() throws TaskListIsEmptyException {
		// Logic to return overdue tasks
		return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to get Task by task id.
	 * @param taskId
	 * @return ResponseEntity containing a Task object and a HTTP status code(200)
	 * @throws TaskDoesntExistException
	 */
	@GetMapping("/taskId/{taskId}")
	public ResponseEntity<Task> getTasksByTaskId(@PathVariable("taskId") int taskId)
			throws TaskDoesntExistException {
		return new ResponseEntity<Task>(taskService.getTasksByTaskId(taskId),HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to get Tasks assigned to a Project id.
	 * @param projectId
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/byprojectId/{projectId}")
	public ResponseEntity<List<Task>> getTasksByProject(@PathVariable("projectId") int projectId)
			throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksByProject(projectId), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch Task count assigned to a Project.
	 * @param projectId
	 * @return ResponseEntity containing a Integer object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/countByproject/{projectId}")
	public ResponseEntity<Integer> getTaskCountOfProject(@PathVariable("projectId") int projectId)
			throws TaskListIsEmptyException {
		return new ResponseEntity<Integer>(taskService.getTaskCountOfProject(projectId), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch Overdue Tasks. 
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/overdue")
	public ResponseEntity<List<Task>> getOverdueTasks() throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getOverdueTasks(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to get Tasks by its status.
	 * @param status
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Task>> getTaskBystatus(@PathVariable("status") String status) throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTaskBystatus( status), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to get all Tasks by priority and status.
	 * @param priority
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/priority/{priority}")
	public ResponseEntity<List<Task>> getTasksByPriorityAndStatus(@PathVariable("priority") String priority) throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksByPriorityAndStatus(priority), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all due soon tasks.
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/due-soon")
	public ResponseEntity<List<Task>> getTasksDueSoon() throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksDueSoon(), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Tasks by user id and status.
	 * @param userId
	 * @param status
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/user/{userId}/status/{status}")
	public ResponseEntity<List<Task>> getTasksByUserAndStatus(@PathVariable("userId") String userId, @PathVariable("status") String status)
			throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksByUserAndStatus(userId, status), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch all Tasks by user id.
	 * @param userId
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable("userId") int userId)
			throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksByUserId(userId), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Tasks by category id.
	 * @param categoryId
	 * @return ResponseEntity containing a List of Task object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable("categoryId") String categoryId)
			throws TaskListIsEmptyException {
		return new ResponseEntity<List<Task>>(taskService.getTasksByCategory(categoryId), HttpStatus.OK);
	}

	/**
	 * Handles HTTP PUT requests to update Task details by task id.
	 * @param taskId
	 * @param taskProjectUserDto
	 * @return ResponseEntity containing a SuccessResponse object and a HTTP status code(200)
	 * @throws TaskDoesntExistException
	 * @throws ProjectDoesNotExistException
	 * @throws UserDoesNotExistException
	 */
	@PutMapping("/update/{taskId}")
	public ResponseEntity<SuccessResponseDto> updateTaskDetailsById(@PathVariable("taskId") int taskId,
			@RequestBody @Valid TaskProjectUserDto taskProjectUserDto) throws TaskDoesntExistException, ProjectDoesNotExistException, UserDoesNotExistException { // Logic

		SuccessResponseDto response = new SuccessResponseDto("UPDATESUCCESS", "Task updated successfully");
		taskService.updateTaskDetailsById(taskId, taskProjectUserDto);
		return new ResponseEntity<SuccessResponseDto>(response, HttpStatus.OK);
	}

	/**
	 * Handles HTTP DELETE requests to delete task using task id.
	 * @param taskId
	 * @return ResponseEntity containing a SuccessResponse object and a HTTP status code(200)
	 * @throws TaskDoesntExistException
	 */
	@DeleteMapping("/{taskId}")
	public ResponseEntity<SuccessResponseDto> deleteTaskById(@PathVariable("taskId") int taskId) throws TaskDoesntExistException {
		// Logic to delete a task

		SuccessResponseDto responseDto = new SuccessResponseDto("DELETESUCCESS", "Task deleted successfully");
		taskService.deleteTaskById(taskId);
		return new ResponseEntity<SuccessResponseDto>(responseDto, HttpStatus.OK);

	}
	
	/**
	 * Handles HTTP GET requests to fetch all Tasks id.
	 * @return ResponseEntity containing a List of integer array object and a HTTP status code(200)
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/getAllTaskIds")
	public ResponseEntity<int[]> getAllTaskIds() throws TaskListIsEmptyException
	{
		return new ResponseEntity<int[]>(taskService.getAllTaskIds(), HttpStatus.OK);
	}
}
