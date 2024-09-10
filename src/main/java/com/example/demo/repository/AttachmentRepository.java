package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer>{

	/**
	 * Custom query to fetch all attachment IDs.
	 * 
	 * @return Array of integers containing all attachment IDs.
	 */
	@Query("Select a.attachmentId from Attachment a")
	int[] getAllAttachmentIds();
	
	
	//DELETE ATTACHMENT BY ATTACHMENTID
	/**
	 * Query to delete attachments by attachment ID
	 * 
	 * @param attachmentId Id of the attachment to be deleted
	 * 
	 */
	
	@Modifying
    @Query("DELETE FROM Attachment a WHERE a.attachmentId = :attachmentId")
    void deleteById(@Param("attachmentId") int attachmentId);
	
	//DELETE ATTACHMENT BY TASKID
	/**
	 * Custom query to delete attachments by task id
	 * 
	 * @param taskId Id of the attachment to be deleted
	 */
	
    @Modifying
	@Query("DELETE from Attachment a where a.task.taskId=:taskId")
	void deleteByTaskId(@Param("taskId")int taskId);
    
    /**
     * Custom query to fetch attachments by attachment id 
     * 
     * @param attachmentId the ID of the attachment to be retrieved
     * @return the Attachment object with the specified ID
     */
    @Query("Select a from Attachment a WHERE a.attachmentId = :attachmentId")
    Attachment getAttachmentById(@Param("attachmentId")int attachmentId);
    
    /**
     * Custom query to fetch attachments by task id
     * 
     * @param taskId the ID of the task whose attachments are to be retrieved
     * @return a list of Attachment objects associated with the specified task ID
     */
    
    @Query("select a from Attachment a where a.task.taskId=:taskId")
    List<Attachment> getAttachmentByTaskId(@Param("taskId")int taskId);
     
    
}