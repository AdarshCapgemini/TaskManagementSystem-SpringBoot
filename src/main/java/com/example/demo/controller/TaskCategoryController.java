package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssociateTaskWithCategoryDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CategoryListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.TaskListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/taskcategories")
@CrossOrigin("http://localhost:4200")
public class TaskCategoryController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * Handles HTTP POST requests to fetch association of Task with Category.
	 * @param request
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> associateTaskWithCategory(@RequestBody @Valid AssociateTaskWithCategoryDto request)
	{
		taskService.associateTaskWithCategory(request.getTaskId(), request.getCategoryId());
		SuccessResponseDto response=new SuccessResponseDto("POSTSUCCESS","TaskCategory addded successfully");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	/**
	 * Handles HTTP GET requests to fetch all tasks for Category.
	 * @param categoryId
	 * @return ResponseEntity containing a List of object array and an HTTP status code(200)
	 * @throws CategoryDoesntExistException
	 * @throws TaskListIsEmptyException
	 */
	@GetMapping("/getTasksForCategory/{categoryId}")
	public ResponseEntity<List<Object[]>> getAllTasksForCategory(@PathVariable("categoryId")int categoryId) throws CategoryDoesntExistException,TaskListIsEmptyException {
		List<Object[]> allTaskForCategory = taskService.getAllTaskForCategory(categoryId);
		return new ResponseEntity<List<Object[]>>(allTaskForCategory,HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch all Category for task using taskId.
	 * @param taskId
	 * @return ResponseEntity containing a List of object array and an HTTP status code(200)
	 * @throws CategoryListIsEmptyException
	 * @throws TaskDoesntExistException
	 */
	@GetMapping("/getCategoriesForTask/{taskId}")
	public ResponseEntity<List<Object[]>> getAllCategoryForTask(@PathVariable("taskId")int taskId) throws TaskDoesntExistException,CategoryListIsEmptyException {
		List<Object[]> allCategoryForTask = categoryService.getAllCategoryForTask(taskId);
		return new ResponseEntity<List<Object[]>>(allCategoryForTask,HttpStatus.OK);
	}
}