package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentTaskUserDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.exception.CommentAlreadyExistException;
import com.example.demo.exception.CommentDoesntExistException;
import com.example.demo.exception.CommentListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
     * Creates a new comment.
     * @param commentTaskUserDto Data transfer object containing comment details, including the associated task and user IDs.
     * @return The created Comment entity.
     * @throws CommentAlreadyExistException If a comment with the same ID already exists.
     * @throws TaskDoesntExistException If the task associated with the comment does not exist.
     * @throws UserDoesNotExistException If the user associated with the comment does not exist.
     */
	@Override
	public Comment createComment(CommentTaskUserDto commentTaskUserDto) throws CommentAlreadyExistException, TaskDoesntExistException, UserDoesNotExistException{
		
		Comment comment = new Comment();
		
		comment.setCommentId(commentTaskUserDto.getCommentId());
		comment.setText(commentTaskUserDto.getText());
		comment.setCreatedAt(commentTaskUserDto.getCreatedAt());
		
		
		if(commentRepository.findById(comment.getCommentId()).isPresent()) {
			throw new CommentAlreadyExistException("Comment already exist");
		}
		else {
			Task task = taskRepository.findById(commentTaskUserDto.getTaskId())
					.orElseThrow(()->new TaskDoesntExistException("Task doesn't exist"));
			User user = userRepository.findById(commentTaskUserDto.getUserId())
					.orElseThrow(()->new UserDoesNotExistException("User doesn't exist"));
			comment.setTask(task);
			comment.setUser(user);
			return commentRepository.save(comment);
		}
	}

	/**
     * Retrieves all comments.
     * @return A list of all Comment entities.
     * @throws CommentListIsEmptyException If no comments are found.
     */
	@Override
	public List<Comment> getAllComments() throws CommentListIsEmptyException{
		if(commentRepository.findAll().isEmpty()) {
			throw new CommentListIsEmptyException("Comment list is empty");
		}
		else {
			return commentRepository.findAll();
		}
	}

	/**
     * Retrieves a comment by its ID.
     * @param commentId The ID of the comment to retrieve.
     * @return The Comment entity with the specified ID.
     * @throws CommentDoesntExistException If no comment with the given ID is found.
     */
	@Override
	public Comment getCommentById(int commentId) throws CommentDoesntExistException{

		if(commentRepository.findById(commentId).isEmpty()) {
			throw new CommentDoesntExistException("Comment doesn't exist");
		}

		else {
			return commentRepository.findById(commentId).orElse(null);
		}
	}
	
	/**
     * Retrieves comments associated with a specific task ID.
     * @param taskId The ID of the task whose comments are to be retrieved.
     * @return A list of Comment entities associated with the specified task ID.
     * @throws CommentListIsEmptyException If no comments are found for the given task ID.
     */
	public List<Comment>  getCommentByTaskId(int taskId) throws CommentListIsEmptyException{
		if(commentRepository.findById(taskId).isEmpty()) {
			throw new CommentListIsEmptyException("Comment list is empty");
		}

		else {
			return commentRepository.getCommentByTaskId(taskId);
		}
	}
	
	/**
     * Updates an existing comment by its ID.
     * @param commentId The ID of the comment to update.
     * @param commentTaskUserDto Data transfer object containing updated comment details, including the associated task and user IDs.
     * @return The updated Comment entity.
     * @throws CommentDoesntExistException If no comment with the given ID is found.
     * @throws TaskDoesntExistException If the task associated with the comment does not exist.
     * @throws UserDoesNotExistException If the user associated with the comment does not exist.
     */
	@Override
	public Comment updateComment(int commentId ,CommentTaskUserDto commentTaskUserDto) throws  TaskDoesntExistException, UserDoesNotExistException, CommentDoesntExistException{
		Comment comment = new Comment();
		
		comment.setCommentId(commentId);
		comment.setText(commentTaskUserDto.getText());
		comment.setCreatedAt(commentTaskUserDto.getCreatedAt());
		
		
		if(!commentRepository.findById(comment.getCommentId()).isPresent()) {
			throw new CommentDoesntExistException("Comment doesn't exist");
		}
		else {
			Task task = taskRepository.findById(commentTaskUserDto.getTaskId())
					.orElseThrow(()->new TaskDoesntExistException("Task doesn't exist"));
			User user = userRepository.findById(commentTaskUserDto.getUserId())
					.orElseThrow(()->new UserDoesNotExistException("User doesn't exist"));
			comment.setTask(task);
			comment.setUser(user);
			return commentRepository.save(comment);
		}
	}

	/**
     * Deletes a comment by its ID.
     * @param commentId The ID of the comment to delete.
     * @throws CommentDoesntExistException If no comment with the given ID is found.
     */
	@Override
	public void deleteComment(int commentId) throws CommentDoesntExistException{

		if(commentRepository.findById(commentId).isEmpty()) {
			throw new CommentDoesntExistException("Comment doesn't exist");
		}
		else {
			commentRepository.deleteById(commentId);
		}
	}
	
	@Override
	public int[] getAllCommentIds() {
		return commentRepository.getAllCommentIds();
	}
}
