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

import com.example.demo.dto.AttachmentTaskDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.Attachment;
import com.example.demo.exception.AttachmentAlreadyExistsException;
import com.example.demo.exception.AttachmentDoesNotExistsException;
import com.example.demo.exception.AttachmentListEmptyException;
import com.example.demo.exception.NoAttachmentFoundException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.service.AttachmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/attachments")
@CrossOrigin("http://localhost:4200")
public class AttachmentController {
	@Autowired
	AttachmentService attachmentService;

	/**
	 * Handles HTTP POST requests to create an attachment.
	 * @param attachentTaskDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws AttachmentAlreadyExistsException
	 * @throws TaskDoesntExistException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createAttachment(@RequestBody @Valid AttachmentTaskDto attachmentTaskDto)
			throws AttachmentAlreadyExistsException, TaskDoesntExistException {
		
		attachmentService.createAttachment(attachmentTaskDto);
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS", "Attachment added successfully");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Handles HTTP GET requests to get all attachments.
	 * @return ResponseEntity containing a List of Attachment object and an HTTP status code(200)
	 * @throws AttachmentListEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Attachment>> getAllAttachments() throws AttachmentListEmptyException {
		return new ResponseEntity<List<Attachment>>(attachmentService.getAllAttachments(), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch attachment object by attachment Id.
	 * @param attachmentId
	 * @return ResponseEntity containing an Attachment object and an HTTP status code(200)
	 * @throws NoAttachmentFoundException
	 */
	@GetMapping("/{attachmentId}")
	public ResponseEntity<Attachment> getAttachmentById(@PathVariable("attachmentId") int attachmentId)
			throws NoAttachmentFoundException {
		return new ResponseEntity<Attachment>(attachmentService.getAttachmentById(attachmentId), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch attachment object by task Id.
	 * @param taskId
	 * @return ResponseEntity containing a List of Attachment object and an HTTP status code(200)
	 * @throws AttachmentListEmptyException
	 */
	@GetMapping("/bytaskId/{taskId}")
	public ResponseEntity<List<Attachment>> getAttachmentByTaskId(@PathVariable("taskId") int taskId)
			throws  AttachmentListEmptyException {
		return new ResponseEntity<List<Attachment>>(attachmentService.getAttachmentByTaskId(taskId), HttpStatus.OK);
	}

	/**
	 * Handles HTTP PUT requests to update an existing Attachment.
	 * @param attachmentId
	 * @param attachmentTaskDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws AttachmentDoesNotExistsException
	 * @throws TaskDoesntExistException
	 */
	@PutMapping("/update/{attachmentId}")
	public ResponseEntity<SuccessResponseDto> updateAttachment(@PathVariable("attachmentId") int attachmentId,
			@RequestBody @Valid AttachmentTaskDto attachmentTaskDto) throws AttachmentDoesNotExistsException, TaskDoesntExistException {
		attachmentService.updateAttachment(attachmentId, attachmentTaskDto);
		SuccessResponseDto response = new SuccessResponseDto("UPDATESUCCESS", "Attachment updated successfully");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Handles HTTP DELETE requests to delete an attachment.
	 * @param attachmentId
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws AttachmentDoesNotExistsException
	 */
	@DeleteMapping("/delete/{attachmentId}")
	public ResponseEntity<SuccessResponseDto> deleteAttachment(@PathVariable("attachmentId") int attachmentId)
			throws AttachmentDoesNotExistsException {
		SuccessResponseDto response = new SuccessResponseDto("DLTSUCCESS", "Attachment delete successfully");
		attachmentService.deleteAttachment(attachmentId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/allattachmentIds")
	public ResponseEntity<int[]> getAllAttachmentIds()
	{
		return new ResponseEntity<int[]>(attachmentService.getAllAttachmentIds(), HttpStatus.OK);
	}

}
