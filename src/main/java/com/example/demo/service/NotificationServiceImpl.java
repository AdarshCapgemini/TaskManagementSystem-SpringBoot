package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NotificationUserDto;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.exception.NotificationAlreadyExistException;
import com.example.demo.exception.NotificationDoesntExistException;
import com.example.demo.exception.NotificationListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	UserRepository userRepository;

	/**
     * Creates a new notification.
     * @param notificationUserDto Data transfer object containing notification details and user ID.
     * @return The created Notification entity.
     * @throws NotificationAlreadyExistException If a notification with the same ID already exists.
     * @throws UserDoesNotExistException If the user associated with the notification does not exist.
     */
	@Override
	public Notification createNotification(NotificationUserDto notificationUserDto)
			throws NotificationAlreadyExistException, UserDoesNotExistException {

		Notification notification = new Notification();
		notification.setNotificationId(notificationUserDto.getNotificationId());
		notification.setCreatedAt(notificationUserDto.getCreatedAt());
		notification.setText(notificationUserDto.getText());

		if (notificationRepository.findById(notification.getNotificationId()).isPresent()) {
			throw new NotificationAlreadyExistException("Notification already exist");
		} else {
			User user = userRepository.findById(notificationUserDto.getUserId())
					.orElseThrow(() -> new UserDoesNotExistException("User doesn't exist"));
			notification.setUser(user);
			return notificationRepository.save(notification);
		}
	}

	/**
     * Retrieves all notifications.
     * @return A list of all Notification entities.
     * @throws NotificationListIsEmptyException If no notifications are found.
     */
	@Override
	public List<Notification> getAllNotifications() throws NotificationListIsEmptyException {
		if (notificationRepository.findAll().isEmpty()) {
			throw new NotificationListIsEmptyException("Notification list is empty");
		} else {
			return notificationRepository.findAll();
		}
	}

	/**
     * Retrieves a notification by its ID.
     * @param notificationId The ID of the notification to retrieve.
     * @return The Notification entity with the specified ID.
     * @throws NotificationDoesntExistException If no notification with the given ID is found.
     */
	@Override
	public Notification getNotificationById(int notificationId) throws NotificationDoesntExistException {
		if (!notificationRepository.findById(notificationId).isPresent()) {
			throw new NotificationDoesntExistException("Notification doesn't exist");
		} else {
			return notificationRepository.findById(notificationId).orElse(null);
		}
	}

	/**
     * Updates an existing notification.
     * @param notificationId The ID of the notification to update.
     * @param notificationUserDto Data transfer object containing updated notification details and user ID.
     * @return The updated Notification entity.
     * @throws NotificationDoesntExistException If no notification with the given ID is found.
     * @throws UserDoesNotExistException If the user associated with the notification does not exist.
     */
	@Override
	public Notification updateNotification(int notificationId,NotificationUserDto notificationUserDto) 
			throws NotificationDoesntExistException, UserDoesNotExistException {

		Notification notification = new Notification();
		notification.setNotificationId(notificationId);
		notification.setCreatedAt(notificationUserDto.getCreatedAt());
		notification.setText(notificationUserDto.getText());

		if (!notificationRepository.findById(notificationId).isPresent()) {
			throw new NotificationDoesntExistException("Notification doesn't exist");
		} else {
			User user = userRepository.findById(notificationUserDto.getUserId())
					.orElseThrow(() -> new UserDoesNotExistException("User doesn't exist"));
			notification.setUser(user);
			return notificationRepository.save(notification);
		}
	}

	/**
     * Deletes a notification by its ID.
     * @param notificationId The ID of the notification to delete.
     * @throws NotificationDoesntExistException If no notification with the given ID is found.
     */
	@Override
	public void deleteNotification(int notificationId) throws NotificationDoesntExistException {
		if (notificationRepository.findById(notificationId).isEmpty()) {
			throw new NotificationDoesntExistException("Notification doesn't exist");
		} else {
			notificationRepository.deleteById(notificationId);
		}

	}
	
	/**
     * Retrieves notifications associated with a specific user ID.
     * @param userId The ID of the user whose notifications are to be retrieved.
     * @return A list of Notification entities associated with the specified user ID.
     * @throws UserDoesNotExistException If the user associated with the notifications does not exist.
     */
	@Override
	public List<Notification> getNotificationByuserId(int userId) throws UserDoesNotExistException{
		if (userRepository.findById(userId).isEmpty()) {
			throw new UserDoesNotExistException("User doesn't exist");
		} else {
			return notificationRepository.getNotificationByuserId(userId);
		}
	}

}
