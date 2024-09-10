package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "COMMENT")
@JsonIgnoreProperties
public class Comment {
	@Id
    @Column(name = "COMMENTID")
    @NotNull(message = "Comment ID cannot be null")
    private int commentId;

    @Column(name = "TEXT", columnDefinition = "TEXT")
    @NotNull(message = "Text cannot be null")
    @Size(min = 1, max = 1000, message = "Text must be between 1 and 1000 characters")
    private String text;

    @Column(name = "CREATEDAT")
    @NotNull(message = "Created At cannot be null")
    @PastOrPresent(message = "Created At must be in the past or present")
    private LocalDateTime createdAt;

	// Mapping MANY-ONE With TASK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TASKID")
	@JsonBackReference
	@JsonIgnore
	private Task task;

	// Mapping MANY-TO-ONE with User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	@JsonBackReference
	@JsonIgnore
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	// constructor
	public Comment() {
	}

	public Comment(int commentId, String text, LocalDateTime createdAt) {
		super();
		this.commentId = commentId;
		this.text = text;
		this.createdAt = createdAt;

	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
