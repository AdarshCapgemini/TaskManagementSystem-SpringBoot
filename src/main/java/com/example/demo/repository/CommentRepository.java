package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	
	/**
	 * Retrieves a list of comments by the task ID.
	 *
	 * @param taskId the ID of the task whose comments are to be retrieved
	 * @return a list of Comment objects associated with the specified task ID
	 */
	
	@Query("select c from Comment c where c.task.taskId=:taskId")
	List<Comment> getCommentByTaskId(@Param("taskId")int taskId);
	
	
	/**
	 * 
	 * Retrieves all comment IDs.
     *
     * @return an array of integers containing all comment IDs 
	 */
	@Query("Select c.commentId from Comment c")
	int[] getAllCommentIds();
	
}
