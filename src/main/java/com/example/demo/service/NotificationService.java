package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.NotificationUserDto;
import com.example.demo.entity.Notification;
import com.example.demo.exception.NotificationAlreadyExistException;
import com.example.demo.exception.NotificationDoesntExistException;
import com.example.demo.exception.NotificationListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;

public interface NotificationService {
	
	Notification createNotification(NotificationUserDto notificationUserDto) throws NotificationAlreadyExistException,UserDoesNotExistException;
    List<Notification> getAllNotifications() throws NotificationListIsEmptyException;
    Notification getNotificationById(int notificationId) throws NotificationDoesntExistException;
    Notification updateNotification(int notificationId, NotificationUserDto notificationUserDto) throws NotificationDoesntExistException, UserDoesNotExistException;
    void deleteNotification(int notificationId) throws NotificationDoesntExistException;    
    List<Notification> getNotificationByuserId(int userId) throws UserDoesNotExistException;
}
