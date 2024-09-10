package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CommentTaskUserDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.Comment;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CommentAlreadyExistException;
import com.example.demo.exception.CommentDoesntExistException;
import com.example.demo.exception.CommentListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.service.CommentService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("http://localhost:4200")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
	 * Handles HTTP POST requests to create a new Comment.
	 * @param commentTaskUserDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws CommentAlreadyExistsException
	 * @throws TaskDoesntExistException
	 * @throws UserDoesNotExistException
	 */
    @PostMapping("/post")
    public ResponseEntity<SuccessResponseDto> createComment(@RequestBody @Valid CommentTaskUserDto commentTaskUserDto) throws CommentAlreadyExistException, TaskDoesntExistException, UserDoesNotExistException{
    	
    	SuccessResponseDto responseDto=new SuccessResponseDto("POSTSUCCESS", "Comments added successfully");
    	commentService.createComment(commentTaskUserDto);
    	return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    	
    }

    /**
	 * Handles HTTP GET requests to fetch all comments.
	 * @return ResponseEntity containing a List of Comment object and an HTTP status code(200)
	 * @throws CommentListIsEmptyException
	 */
    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() throws CommentListIsEmptyException{
    	return new ResponseEntity<List<Comment>>(commentService.getAllComments(),HttpStatus.OK);
    }
    
    /**
	 * Handles HTTP GET requests to update an existing address.
	 * @param commentId
	 * @return ResponseEntity containing a Comment object and an HTTP status code(200)
	 * @throws CommentDoesntExistException
	 */
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") int commentId) throws CommentDoesntExistException{
    	
    	return new ResponseEntity<Comment>(commentService.getCommentById(commentId), HttpStatus.OK);
    }
    
    /**
	 * Handles HTTP GET requests to fetch Comment object by Task id.
	 * @param taskId
	 * @return ResponseEntity containing a List of Comment object and an HTTP status code(200)
	 * @throws CommentListIsEmptyException
	 */
    @GetMapping("/bytaskId/{taskId}")
    public ResponseEntity<List<Comment>> getCommentByTaskId(@PathVariable("taskId") int taskId) throws  CommentListIsEmptyException{
    	
    	return new ResponseEntity<List<Comment>>(commentService.getCommentByTaskId(taskId), HttpStatus.OK);
    }

    /**
	 * Handles HTTP PUT requests to update an existing comment.
	 * @param commentId
	 * @param commentTaskUserDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws TaskDoesntExistException
	 * @throws UserDoesNotExistException
	 * @throws CommentDoesntExistException
	 */
    @PutMapping("/update/{commentId}")
    public ResponseEntity<SuccessResponseDto> updateComment(@PathVariable("commentId") int commentId,@RequestBody @Valid CommentTaskUserDto commentTaskUserDto) throws  TaskDoesntExistException, UserDoesNotExistException, CommentDoesntExistException{
    	
    	SuccessResponseDto responseDto=new SuccessResponseDto("UPDATEDSUCCESS", "Comment updated successfully");
    	commentService.updateComment(commentId , commentTaskUserDto);
    	return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    /**
	 * Handles HTTP DELETE requests to delete Comment object by comment id.
	 * @param commentId
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(204)
	 * @throws CommentDoesntExistException
	 */
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<SuccessResponseDto> deleteComment(@PathVariable("commentId") int commentId) throws CommentDoesntExistException{
    	
    	SuccessResponseDto responseDto=new SuccessResponseDto("DELETESUCCESS", "Comment deleted successfullly");
    	commentService.deleteComment(commentId);
    	return new ResponseEntity<>(responseDto,HttpStatus.NO_CONTENT);
    	
    }
    
    @GetMapping("/allcommentids")
	public ResponseEntity<int[]> getAllCommentIds() throws UserListIsEmptyException
	{
		return new ResponseEntity<int[]>(commentService.getAllCommentIds(), HttpStatus.OK);
	}

}
