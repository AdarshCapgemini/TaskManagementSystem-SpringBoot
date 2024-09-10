package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AttachmentTaskDto;
import com.example.demo.entity.Attachment;
import com.example.demo.entity.Task;
import com.example.demo.exception.AttachmentAlreadyExistsException;
import com.example.demo.exception.AttachmentDoesNotExistsException;
import com.example.demo.exception.AttachmentListEmptyException;
import com.example.demo.exception.NoAttachmentFoundException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
    AttachmentRepository attachmentRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	
	/**
     * Creates a new attachment.
     * @param attachmentTaskDto Data transfer object containing attachment details.
     * @return The created Attachment entity.
     * @throws AttachmentAlreadyExistsException If an attachment with the given ID already exists.
     * @throws TaskDoesntExistException If the task associated with the attachment does not exist.
     */ 
	@Override
	public Attachment createAttachment(AttachmentTaskDto attachmentTaskDto) throws AttachmentAlreadyExistsException, TaskDoesntExistException {
		
		Attachment attachment = new Attachment();
		attachment.setAttachmentId(attachmentTaskDto.getAttachmentId());
		attachment.setFileName(attachmentTaskDto.getFileName());
		attachment.setFilePath(attachmentTaskDto.getFilePath());
		
		if(attachmentRepository.findById(attachment.getAttachmentId()).isPresent())	{
			throw new AttachmentAlreadyExistsException("Attachment already exist");
		}else {
			Task task = taskRepository.findById(attachmentTaskDto.getTaskId())
		            .orElseThrow(() -> new TaskDoesntExistException("Task doesn't exist"));
			attachment.setTask(task);
			
			return attachmentRepository.save(attachment);
		}
	}

	/**
     * Retrieves all attachments.
     * @return A list of all Attachment entities.
     * @throws AttachmentListEmptyException If no attachments are found.
     */  
    @Override
    public List<Attachment> getAllAttachments() throws AttachmentListEmptyException{
        return attachmentRepository.findAll();
    }
    
    /**
     * Retrieves an attachment by its ID.
     * @param attachmentId The ID of the attachment to retrieve.
     * @return The Attachment entity.
     * @throws NoAttachmentFoundException If no attachment with the given ID is found.
     */
    @Override
    public Attachment getAttachmentById(int attachmentId) throws NoAttachmentFoundException{
        if(attachmentRepository.findById(attachmentId).isEmpty())
        {
            throw new NoAttachmentFoundException("Attachment doesn't exist");
        }else 
        return attachmentRepository.getAttachmentById(attachmentId);
            
    }
    
    /**
     * Updates an existing attachment.
     * @param attachmentId The ID of the attachment to update.
     * @param attachmentTaskDto Data transfer object containing updated attachment details.
     * @return The updated Attachment entity.
     * @throws AttachmentDoesNotExistsException If the attachment to update does not exist.
     * @throws TaskDoesntExistException If the task associated with the attachment does not exist.
     */
    @Override
    public Attachment updateAttachment(int attachmentId, AttachmentTaskDto attachmentTaskDto) throws AttachmentDoesNotExistsException, TaskDoesntExistException {
    	Attachment attachment = new Attachment();
		attachment.setAttachmentId(attachmentId);
		attachment.setFileName(attachmentTaskDto.getFileName());
		attachment.setFilePath(attachmentTaskDto.getFilePath());
		
		if(!attachmentRepository.findById(attachment.getAttachmentId()).isPresent())	{
			throw new AttachmentDoesNotExistsException("Attachment doesn't exist");
		}else {
			Task task = taskRepository.findById(attachmentTaskDto.getTaskId())
		            .orElseThrow(() -> new TaskDoesntExistException("Task doesn't exist"));
			attachment.setTask(task);
			
			return attachmentRepository.save(attachment);
		}
    }
    
    /**
     * Deletes an attachment by its ID.
     * @param attachmentId The ID of the attachment to delete.
     * @return The deleted Attachment entity.
     * @throws AttachmentDoesNotExistsException If no attachment with the given ID is found.
     */
    @Override
    @Transactional
    public Attachment deleteAttachment(int attachmentId) throws AttachmentDoesNotExistsException{
        if(attachmentRepository.findById(attachmentId).isEmpty())
        {
            throw new AttachmentDoesNotExistsException("Attachment doesn't exist");
        }
        else
        {
            Attachment attachment=attachmentRepository.findById(attachmentId).get();
        attachmentRepository.deleteById(attachmentId);
        return attachment;
        }
    }
    
    /**
     * Retrieves attachments associated with a specific task ID.
     * @param taskId The ID of the task whose attachments are to be retrieved.
     * @return A list of Attachment entities associated with the specified task ID.
     * @throws AttachmentListEmptyException If no attachments are found for the given task ID.
     */
    @Override
    public List<Attachment> getAttachmentByTaskId(int taskId) throws AttachmentListEmptyException{
    	 if(attachmentRepository.findAll().isEmpty())
         {
             throw new AttachmentListEmptyException("Attachment List is Empty");
         }else
         {
        	 return attachmentRepository.getAttachmentByTaskId(taskId);
         }
    }
    /**
     * Retrieves all attachment IDs. 
     * @return An array of integers representing the IDs of all attachments.
     */
    @Override
	public int[] getAllAttachmentIds() {
		return attachmentRepository.getAllAttachmentIds();
	}
	
}
