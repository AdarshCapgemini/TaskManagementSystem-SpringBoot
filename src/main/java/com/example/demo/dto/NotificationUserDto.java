package com.example.demo.dto;
 
import java.time.LocalDateTime;
 
public class NotificationUserDto {
	 private int notificationId;
	 private String text;
	 private LocalDateTime createdAt;
	 private int userId;
 
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
 
	public int getUserId() {
		return userId;
	}
 
	public void setUserId(int userId) {
		this.userId = userId;
	}


 
}