package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CommentTaskUserDto;
import com.example.demo.entity.Comment;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CommentAlreadyExistException;
import com.example.demo.exception.CommentDoesntExistException;
import com.example.demo.exception.CommentListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.exception.UserDoesNotExistException;

public interface CommentService {
    Comment createComment(CommentTaskUserDto commentTaskUserDto)throws CommentAlreadyExistException, TaskDoesntExistException, UserDoesNotExistException;
    List<Comment> getAllComments()throws CommentListIsEmptyException;
    Comment getCommentById(int commentId)throws CommentDoesntExistException;
    Comment updateComment(int commentId ,CommentTaskUserDto commentTaskUserDto) throws  TaskDoesntExistException, UserDoesNotExistException, CommentDoesntExistException;
    void deleteComment(int commentId)throws CommentDoesntExistException;
    
    List<Comment>  getCommentByTaskId(int taskId) throws CommentListIsEmptyException;
    int[] getAllCommentIds();
}
