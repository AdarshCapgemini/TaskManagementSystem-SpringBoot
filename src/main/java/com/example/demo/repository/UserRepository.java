package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	/**
	 * Custom query to fetch details of a specific user by their user ID.
	 * 
	 * @param userId The ID of the user to be fetched.
	 * @return User entity that matches the given user ID.
	 */
	@Query("SELECT u FROM User u WHERE u.userId=:userId")
	User getDetailsOfSpecificUserById(@Param("userId")int userId);
	
	/**
	 * Custom query to fetch users with a specific email domain.
	 * 
	 * @param domain The email domain to filter users by.
	 * @return List of User entities that have the specified email domain.
	 */
	@Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
	List<User> getUsersWithSpecificEmailDomain(@Param("domain")String domain);
	
	
	/**
	 * Custom query to fetch users by their full name.
	 * 
	 * @param fullName The full name of the users to be fetched.
	 * @return List of User entities that match the given full name.
	 */
	@Query("SELECT u FROM User u WHERE u.fullName=:fullName")
	List<User> getUsersByFullName(@Param("fullName")String fullName);
	
	
	/**
	 * Custom query to fetch users with the most tasks.
	 * 
	 * @return List of User entities that have the highest number of tasks.
	 */

	@Query("SELECT u FROM User u WHERE SIZE(u.tasks) = (SELECT MAX(SIZE(u2.tasks)) FROM User u2)")
	List<User> getUsersWithMostTasks();
	
	/**
	 * Custom query to fetch the user ID by their username.
	 * 
	 * @param userName The username of the user to be fetched.
	 * @return The user ID of the user that matches the given username.
	 */
	@Query("select u.userId from User u where u.userName=:userName")
	int getUserIdByUserName(@Param("userName")String userName);


	/**
	 * Custom query to fetch users who have completed tasks.
	 * 
	 * @return List of User entities that have tasks with 'Completed' status.
	 */
	@Query("SELECT DISTINCT u FROM User u JOIN u.tasks t WHERE t.status = 'Completed'")
	List<User> getUserWithCompletedTasks();
	
	/**
	 * Method to find a user by their username.
	 * 
	 * @param userName The username of the user to be fetched.
	 * @return User entity that matches the given username.
	 */
	User findByUserName(@Param("userName")String userName);
	
	
	/**
	 * Custom native query to fetch all user role IDs by username.
	 * 
	 * @param userName The username of the user whose role IDs are to be fetched.
	 * @return List of integers containing user role IDs associated with the given username.
	 */
	@Query(value = "SELECT ur.userRoleId " +
            "FROM user u " +
            "JOIN userroles ur ON u.userId = ur.userId " +
            "JOIN userrole r ON ur.userRoleId = r.userRoleId " +
            "WHERE u.userName = :userName", nativeQuery = true)
	List<Integer> getAllUserRoleIdsByUserName(@Param("userName") String userName);
	
	
	/**
	 * Custom query to fetch all user roles associated with a specific user ID.
	 * 
	 * @param userId The ID of the user whose roles are to be fetched.
	 * @return List of UserRole entities associated with the given user ID.
	 */
	@Query("SELECT ur FROM User u JOIN u.userRoles ur WHERE u.userId = :userId")
	List<UserRole> getAllUserRolesWithUserId(@Param("userId") int userId);
	
	
	/**
	 * Custom native query to fetch all users along with their roles.
	 * 
	 * @return List of Object arrays containing user details and their roles.
	 */
	@Query(value = "SELECT u.userId, u.fullName, u.email, GROUP_CONCAT(r.roleName SEPARATOR ', ') as roles " +
            "FROM User u " +
            "JOIN UserRoles ur ON u.userId = ur.userId " +
            "JOIN UserRole r ON ur.userRoleId = r.userRoleId " +
            "GROUP BY u.userId, u.fullName, u.email " +
            "ORDER BY u.userId", 
    nativeQuery = true)
	List<Object[]> getAllUserWithUserRole();
	
	
	/**
	 * Custom query to fetch all user IDs.
	 * 
	 * @return Array of integers containing all user IDs.
	 */
	@Query("Select u.userId from User u")
	int[] getAllUserIds();
	
	
}
