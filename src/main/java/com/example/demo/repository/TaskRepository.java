package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;

import jakarta.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	
	/**
	 * Custom query to fetch tasks that are overdue.
	 * 
	 * @return List of Task entities that have a due date earlier than the current date.
	 */
	@Query("SELECT t FROM Task t WHERE t.dueDate < CURRENT_DATE")
	public List<Task> getOverdueTasks();
	
	
	/**
	 * Custom query to fetch tasks based on their status.
	 * 
	 * @param status The status of the tasks to be fetched.
	 * @return List of Task entities that match the given status.
	 */
	@Query("select t from Task t where t.status=:status")
	List<Task> getTaskBystatus(@Param("status")String status); 
	
	
	/**
	 * Custom query to fetch tasks based on their priority and status.
	 * 
	 * @param priority The priority of the tasks to be fetched.
	 * @param status The status of the tasks to be fetched.
	 * @return List of Task entities that match the given priority and status.
	 */
	@Query("select t from Task t where t.priority =:priority")
	public List<Task> getTasksByPriorityAndStatus(@Param("priority")String priority);
	
	
	/**
	 * Custom native query to fetch tasks that are due soon (within the next 3 days).
	 * 
	 * @return List of Task entities that are due within the next 3 days from the current date.
	 */
	@Query(value = "SELECT * FROM Task t WHERE t.dueDate < DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY) AND t.dueDate > CURRENT_DATE", nativeQuery = true)
	List<Task> getTasksDueSoon();

	
	/**
	 * Custom query to fetch tasks assigned to a specific user.
	 * 
	 * @param userId The ID of the user whose tasks are to be fetched.
	 * @return List of Task entities assigned to the given user ID.
	 */
	
	@Query("select t from Task t where t.user.userId=:userId")
	List<Task> getTasksByUserId(@Param("userId")int userId);
	
	
	/**
	 * Custom query to fetch tasks associated with a specific project.
	 * 
	 * @param projectId The ID of the project whose tasks are to be fetched.
	 * @return List of Task entities associated with the given project ID.
	 */
	@Query("select t from Task t where t.project.projectId=:projectId")
	List<Task> getTasksByProject(@Param("projectId")int  projectId);
	
	
	/**
	 * Custom query to count the number of tasks associated with a specific project.
	 * 
	 * @param projectId The ID of the project whose tasks are to be counted.
	 * @return The count of Task entities associated with the given project ID.
	 */
	@Query("select count(t) from Task t where t.project.projectId=:projectId ")
	int getTaskCountOfProject(@Param("projectId")int  projectId);
	
	
	/**
	 * Custom query to fetch tasks assigned to a specific user and with a specific status.
	 * 
	 * @param userId The ID of the user whose tasks are to be fetched.
	 * @param status The status of the tasks to be fetched.
	 * @return List of Task entities assigned to the given user ID and matching the given status.
	 */
	@Query("select t from Task t where t.user.userId =:userId and t.status=:status")
	public List<Task> getTasksByUserAndStatus(@Param("userId")String userId, @Param("status")String status);
	
	
	/**
	 * Custom query to fetch tasks associated with a specific category.
	 * 
	 * @param categoryId The ID of the category whose tasks are to be fetched.
	 * @return List of Task entities associated with the given category ID.
	 */

	@Query("select t from Task t join t.categories c where c.categoryId = :categoryId")
	public List<Task> getTasksByCategory(@Param("categoryId")String categoryId);
	
	
	/**
	 * Custom query to delete a task based on its task ID.
	 * 
	 * @param taskId The ID of the task to be deleted.
	 */
	@Modifying
	@Query("DELETE from Task t where t.taskId=:taskId")
	void deleteByTaskId(@Param("taskId")int taskId);
	
	
	/**
	 * Custom query to fetch all tasks associated with a specific category.
	 * 
	 * @param categoryId The ID of the category whose tasks are to be fetched.
	 * @return List of Object arrays containing Task entities associated with the given category ID.
	 */

	@Query("SELECT t FROM Task t JOIN t.categories c WHERE c.categoryId = :categoryId")
	List<Object[]> getAllTaskForCategory(@Param("categoryId")int categoryId);
	
	
	/**
	 * Custom query to fetch a task based on its task ID.
	 * 
	 * @param taskId The ID of the task to be fetched.
	 * @return Task entity that matches the given task ID.
	 */
	@Query("select t from Task t where t.taskId=:taskId")
	Task getTasksByTaskId(@Param("taskId")int taskId);
 
	
	/**
	 * Custom native query to associate a task with a category.
	 * 
	 * @param taskId The ID of the task to be associated.
	 * @param categoryId The ID of the category to be associated.
	 */
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO TASKCATEGORY (TASKID, CATEGORYID) VALUES (:taskId, :categoryId)", nativeQuery = true)
    void associateTaskWithCategory(@Param("taskId")int taskId,@Param("categoryId")int categoryId);
	
	/**
	 * Custom query to fetch all task IDs.
	 * 
	 * @return Array of integers containing all task IDs.
	 */
	@Query("Select t.taskId from Task t")
	int[] getAllTaskIds();
	

}
