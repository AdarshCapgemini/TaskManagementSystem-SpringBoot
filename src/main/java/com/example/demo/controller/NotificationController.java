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

import com.example.demo.dto.NotificationUserDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.Notification;
import com.example.demo.exception.AttachmentListEmptyException;
import com.example.demo.exception.CommentListIsEmptyException;
import com.example.demo.exception.AttachmentDoesNotExistsException;
import com.example.demo.exception.NotificationAlreadyExistException;
import com.example.demo.exception.NotificationDoesntExistException;
import com.example.demo.exception.NotificationListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.service.NotificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("http://localhost:4200")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	/**
	 * Handles HTTP POST requests to create Notification object.
	 * @param notificationUserDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws NotiicationAlreadyExistException
	 * @throws UserDoesNotExistException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createNotification(@RequestBody @Valid NotificationUserDto notificationUserDto) throws NotificationAlreadyExistException, UserDoesNotExistException{
		
		notificationService.createNotification(notificationUserDto);
		SuccessResponseDto responseDto=new SuccessResponseDto("POSTSUCCESS", "Notification added successfullly");
		
		return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
	}

	/**
	 * Handles HTTP GET requests to fetch all Notifications.
	 * @return ResponseEntity containing a List of Notification object and an HTTP status code(200)
	 * @throws NotificationListIsEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Notification>> getAllNotifications() throws  NotificationListIsEmptyException{
		return new ResponseEntity<List<Notification>>(notificationService.getAllNotifications(),HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch Notification object by Notification id.
	 * @param taskId
	 * @return ResponseEntity containing a Notification object and an HTTP status code(200)
	 * @throws NotificationDoesntExistException
	 */
	@GetMapping("/{notificationId}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable("notificationId") int notificationId) throws  NotificationDoesntExistException{
		return new ResponseEntity<Notification>(notificationService.getNotificationById(notificationId),HttpStatus.OK);	
	}
	
	/**
	 * Handles HTTP GET requests to fetch Notification object by user id.
	 * @param userId
	 * @return ResponseEntity containing a List of Notification object and an HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/byUserId/{userId}")
	public ResponseEntity<List<Notification>> getNotificationByuserId(@PathVariable("userId") int userId) throws  UserDoesNotExistException{
		return new ResponseEntity<List<Notification>>(notificationService.getNotificationByuserId(userId),HttpStatus.OK);	
	}



	@PutMapping("/{notificationId}")
	public ResponseEntity<SuccessResponseDto> updateNotification(@PathVariable int notificationId,@RequestBody @Valid NotificationUserDto notificationUserDto) throws NotificationDoesntExistException, UserDoesNotExistException{
		SuccessResponseDto responseDto=new SuccessResponseDto("UPDATESUCCESS", "Notification updated successfullly");
		notificationService.updateNotification(notificationId,notificationUserDto);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);

	}

	@DeleteMapping("/{notificationId}")
	public ResponseEntity<SuccessResponseDto> deleteNotification(@PathVariable int notificationId) throws NotificationDoesntExistException{

		SuccessResponseDto responseDto=new SuccessResponseDto("DELETESUCCESS", "Notification deleted successfullly");
		notificationService.deleteNotification(notificationId);
		return new ResponseEntity<>(responseDto,HttpStatus.NO_CONTENT);
	}
}
