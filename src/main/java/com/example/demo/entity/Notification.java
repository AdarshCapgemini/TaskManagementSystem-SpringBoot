package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "NOTIFICATION")
public class Notification {
	@Id
    @Column(name = "NOTIFICATIONID")
    @NotNull(message = "Notification ID cannot be null")
    private int notificationId;

    @Column(name = "TEXT")
    @NotNull(message = "Text cannot be null")
    @Size(min = 1, max = 1000, message = "Text must be between 1 and 1000 characters")
    private String text;

    @Column(name = "CREATEDAT")
    @NotNull(message = "Created At cannot be null")
    @PastOrPresent(message = "Created At must be in the past or present")
    private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	@JsonIgnore
	private User user;

	public Notification() {
	}

	public Notification(int notificationId, String text, LocalDateTime createdAt) {
		super();
		this.notificationId = notificationId;
		this.text = text;
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
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