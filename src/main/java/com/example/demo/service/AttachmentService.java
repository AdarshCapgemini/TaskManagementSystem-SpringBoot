package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AttachmentTaskDto;
import com.example.demo.entity.Attachment;
import com.example.demo.exception.AttachmentAlreadyExistsException;
import com.example.demo.exception.AttachmentDoesNotExistsException;
import com.example.demo.exception.AttachmentListEmptyException;
import com.example.demo.exception.NoAttachmentFoundException;
import com.example.demo.exception.TaskDoesntExistException;

public interface AttachmentService {
	
	Attachment createAttachment(AttachmentTaskDto attachmentTaskDto)throws AttachmentAlreadyExistsException,TaskDoesntExistException;
	List<Attachment> getAllAttachments() throws AttachmentListEmptyException;
    Attachment getAttachmentById(int attachmentId) throws NoAttachmentFoundException;
    Attachment updateAttachment(int attachmentId, AttachmentTaskDto attachmentTaskDto) throws TaskDoesntExistException, AttachmentDoesNotExistsException;
    Attachment deleteAttachment(int attachmentId) throws AttachmentDoesNotExistsException;
    List<Attachment> getAttachmentByTaskId(int taskId) throws AttachmentListEmptyException;
    
    int[] getAllAttachmentIds();
    
}