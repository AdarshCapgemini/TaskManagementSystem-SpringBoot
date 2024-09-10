package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import com.example.demo.dto.ErrorResponseDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//***************************************************************************************************************************************************************
	//TASK EXCEPTIONS
	
	@ExceptionHandler(TaskAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleTaskAlreadyExistsException(TaskAlreadyExistsException ex, WebRequest request)
	{
		return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("ADDFAILS",ex.getMessage()), HttpStatus.CONFLICT);
	}
	
	
	
	
	
	@ExceptionHandler(TaskDoesntExistException.class)
	public ResponseEntity<ErrorResponseDto> handleTaskDoesntExistException(TaskDoesntExistException ex,
			WebRequest request, HttpServletRequest servletRequest) {

		String description = servletRequest.getMethod();

		if (description.contains("PUT")) {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS", ex.getMessage()),
					HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS", ex.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ExceptionHandler(TaskListIsEmptyException.class)
	public ResponseEntity<ErrorResponseDto> handleUserListIsEmptyException(TaskListIsEmptyException ex, WebRequest request) {
	    return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS", ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	

	//***************************************************************************************************************************************************************
	//ATTACHMENT EXCEPTION
	
	//ATTACHMENT DOES NOT EXISTS EXCEPTION
		@ExceptionHandler(AttachmentDoesNotExistsException.class)
		public ResponseEntity<ErrorResponseDto> handlerException(AttachmentDoesNotExistsException ex,WebRequest request,HttpServletRequest servletRequest){
			String description=servletRequest.getMethod();
			if(description.contains("PUT")) 
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
			else if(description.contains("DELETE"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("FAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
			
		}
		
		//ATTACHMENT LIST EMPTY EXCEPTION
		@ExceptionHandler(AttachmentListEmptyException.class)
		public ResponseEntity<ErrorResponseDto> handlerException(AttachmentListEmptyException ex,WebRequest request){
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
		}
		
		//NO ATTACHMENT FOUUND EXCEPTION
		@ExceptionHandler(NoAttachmentFoundException.class)
		public ResponseEntity<ErrorResponseDto> handlerException(NoAttachmentFoundException ex,WebRequest request){
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
		}
		
		//ATTACHMENT ALREADY EXSTS EXCEPTION
		@ExceptionHandler(AttachmentAlreadyExistsException.class)
		public ResponseEntity<ErrorResponseDto> handlerException(AttachmentAlreadyExistsException ex,WebRequest request){
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("POSTFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
		}
		
		//***************************************************************************************************************************************************************
		//CATEGORY EXCEPTIONS
		
		@ExceptionHandler(CategoryDoesntExistException.class)
		public ResponseEntity<ErrorResponseDto> handleCategoryDoesntExistException(CategoryDoesntExistException ex,WebRequest request,HttpServletRequest servletRequest)
		{
			String description = servletRequest.getMethod();
			if(description.contains("PUT"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else if(description.contains("DELETE"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else 
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(CategoryAlreadyExistException.class)
		public ResponseEntity<ErrorResponseDto> handleCategoryAlreadyExistException(CategoryAlreadyExistException ex, WebRequest request,HttpServletRequest servletRequest)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("ADDFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(CategoryListIsEmptyException.class)
		public ResponseEntity<ErrorResponseDto> handleCategoryListIsEmptyException(CategoryListIsEmptyException ex, WebRequest request,HttpServletRequest servletRequest)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
		}
		
		
		//***************************************************************************************************************************************************************
		//COMMENT EXCEPTION
		
		
		@ExceptionHandler(CommentDoesntExistException.class)
		public ResponseEntity<ErrorResponseDto> handleCommentDoesntExistException(CommentDoesntExistException ex,
				WebRequest request, HttpServletRequest servletRequest) {
			String description = servletRequest.getMethod();

			if (description.contains("DELETE")) {
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
			}

		}

		@ExceptionHandler(CommentAlreadyExistException.class)
		public ResponseEntity<ErrorResponseDto> handleCommentAlreadyExistException(CommentAlreadyExistException ex,
				WebRequest request, HttpServletRequest servletRequest) {
			String description = servletRequest.getMethod();

			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("POSTSUCCESS", ex.getMessage()), HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(CommentListIsEmptyException.class)
		public ResponseEntity<ErrorResponseDto> handleCommentListIsEmptyException(CommentListIsEmptyException ex,
				WebRequest request, HttpServletRequest servletRequest) {
			String description = servletRequest.getMethod();

			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
		}
		
		//***************************************************************************************************************************************************************
		//NOTIFICATION EXCEPTION
		
		@ExceptionHandler(NotificationAlreadyExistException.class)
		public ResponseEntity<ErrorResponseDto> handleNotificationAlreadyExistException(NotificationAlreadyExistException ex,
				WebRequest request, HttpServletRequest servletRequest) {
			String description = servletRequest.getMethod();

			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("ADDFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(NotificationDoesntExistException.class)
		public ResponseEntity<ErrorResponseDto> handleNotificationDoesntExistException(NotificationDoesntExistException ex,
				WebRequest request, HttpServletRequest servletRequest) {
			String description = servletRequest.getMethod();
			if(description.contains("PUT")) {
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS", ex.getMessage()), HttpStatus.NOT_FOUND);
			}
		}

		//***************************************************************************************************************************************************************
		//USER EXCEPTION
		

		@ExceptionHandler(UserDoesNotExistException.class)
		public ResponseEntity<ErrorResponseDto> handleUserDoesNotExistException(UserDoesNotExistException ex, WebRequest request, HttpServletRequest servletRequest)
		{
			String description = servletRequest.getMethod();
			if(description.contains("PUT"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else if(description.contains("DELETE"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(UserAlreadyExistsException.class)
		public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("ADDFAILS",ex.getMessage()), HttpStatus.CONFLICT);
		}
		
		@ExceptionHandler(UserListIsEmptyException.class)
		public ResponseEntity<ErrorResponseDto> handleUserListIsEmptyException(UserListIsEmptyException ex, WebRequest request)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS",ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
		//***************************************************************************************************************************************************************
		//USER ROLE EXCEPTION
		
		@ExceptionHandler(UserRoleDoesNotExistException.class)
		public ResponseEntity<ErrorResponseDto> handleUserRoleDoesNotExistException(UserRoleDoesNotExistException ex, WebRequest request, HttpServletRequest servletRequest)
		{
			String description = servletRequest.getMethod();
			if(description.contains("PUT"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else if(description.contains("DELETE"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(UserRoleListIsEmptyException.class)
		public ResponseEntity<ErrorResponseDto> handleUserRoleListIsEmptyException(UserRoleListIsEmptyException ex, WebRequest request)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS",ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(UserRoleAlreadyExistsException.class)
		public ResponseEntity<ErrorResponseDto> handleUserRoleAlreadyExistsException(UserRoleAlreadyExistsException ex, WebRequest request)
		{
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("ADDFAILS",ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
		
		//***************************************************************************************************************************************************************
		//project does not exist exception
		
		@ExceptionHandler(ProjectDoesNotExistException.class)
		public ResponseEntity<ErrorResponseDto> handleProjectDoesNotExistException(ProjectDoesNotExistException ex, WebRequest request, HttpServletRequest servletRequest){
			String errorDescription=servletRequest.getMethod();
			
			if(errorDescription.contains("PUT"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("UPDTFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);
			else if(errorDescription.contains("DELETE"))
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("DLTFAILS",ex.getMessage()),HttpStatus.NOT_FOUND); 
			else
				return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("GETALLFAILS",ex.getMessage()),HttpStatus.NOT_FOUND);	
		}
		
		//***************************************************************************************************************************************************************
		/** Invalid User Exception */
		
		@ExceptionHandler(InvalidUserException.class)
        public ResponseEntity<ErrorResponseDto> handleInvalidUserException(InvalidUserException ex, WebRequest request)
        {
            return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("AUTHFAILS",ex.getMessage()), HttpStatus.NOT_FOUND);
        }
		
		//***************************************************************************************************************************************************************
		//VALIDATION EXCEPTION HANDLING
		@ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach(error -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }


		

}
