package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.TaskProjectUserDto;
import com.example.demo.entity.Task;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.TaskAlreadyExistsException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.TaskListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;

public interface TaskService {

	Task createTask(TaskProjectUserDto taskProjectUserDto)
			throws TaskAlreadyExistsException, ProjectDoesNotExistException, UserDoesNotExistException;
	
	Task getTasksByTaskId(int taskId)throws TaskDoesntExistException;
	List<Task> getAllTasks() throws TaskListIsEmptyException;

	List<Task> getOverdueTasks() throws TaskListIsEmptyException;

	List<Task> getTasksByPriorityAndStatus(String priority) throws TaskListIsEmptyException;

	List<Task> getTasksDueSoon() throws TaskListIsEmptyException;
	
	List<Task> getTasksByUserId(int userId) throws TaskListIsEmptyException ;
	List<Task> getTasksByProject(int  projectId) throws TaskListIsEmptyException ;
	int getTaskCountOfProject(int  projectId) throws TaskListIsEmptyException;
	List<Task> getTaskBystatus(String status) throws TaskListIsEmptyException ;
	
	List<Task> getTasksByUserAndStatus(String userId, String status) throws TaskListIsEmptyException;

	List<Task> getTasksByCategory(String categoryId) throws TaskListIsEmptyException;

	public Task updateTaskDetailsById(int taskId, TaskProjectUserDto taskProjectUserDto) throws TaskDoesntExistException, ProjectDoesNotExistException, UserDoesNotExistException;	
	
	void deleteTaskById(int taskId) throws TaskDoesntExistException;

	List<Object[]> getAllTaskForCategory(int categoryId) throws CategoryDoesntExistException, TaskListIsEmptyException;

	void associateTaskWithCategory(int taskId, List<Integer> categoryId);
	
	int[] getAllTaskIds() throws TaskListIsEmptyException;

}