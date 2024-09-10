package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment {
	@Id
	@Column(name = "ATTACHMENTID")
	private int attachmentId;

	@Column(name = "FILENAME")
	@NotNull(message = "cnnot be null")
	@Size(min = 1, max = 300, message = "Text must be between 1 and 300 characters")
	private String fileName;

	@Column(name = "FILEPATH")
	@NotNull(message = "cannot be null")
	private String filePath;

	// Mapping MANY-ONE With TASK
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TASKID")
	@JsonBackReference
	@JsonIgnore
	private Task task;

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Attachment() {
	}

	public Attachment(int attachmentId, String fileName, String filePath) {
		super();
		this.attachmentId = attachmentId;
		this.fileName = fileName;
		this.filePath = filePath;
	}

}
