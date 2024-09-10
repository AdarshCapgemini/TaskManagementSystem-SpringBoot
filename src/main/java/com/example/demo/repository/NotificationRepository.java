package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer>{
	
	
	/**
	 * Retrieves a list of notifications by the user ID.
	 *
	 * @param userId the ID of the user whose notifications are to be retrieved
	 * @return a list of Notification objects associated with the specified user ID
	 */
	@Query("select n from Notification n where n.user.userId=:userId")
	List<Notification> getNotificationByuserId(@Param("userId")int userId);

}
