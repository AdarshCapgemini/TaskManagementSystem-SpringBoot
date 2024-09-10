package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	
	/**
	 * Custom query to fetch a user role by its ID.
	 * 
	 * @param userRoleId The ID of the user role to be fetched.
	 * @return UserRole entity that matches the given user role ID.
	 */
	@Query("SELECT u FROM UserRole u WHERE u.userRoleId=:userRoleId")
	UserRole getUserRoleById(@Param("userRoleId")int userRoleId);

	

    /**
     * Custom native query to assign a user role to a user.
     * 
     * @param userId The ID of the user to be assigned the role.
     * @param userRoleId The ID of the role to be assigned to the user.
     */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO USERROLES (USERID, USERROLEID) VALUES (:userId, :userRoleId)", nativeQuery = true)
	void assignUserRoleToUser(@Param("userId")int userId,@Param("userRoleId")int userRoleId);

	
	/**
	 * Custom query to fetch all user-role associations.
	 * 
	 * @return List of Object arrays containing user IDs and their associated user role IDs.
	 */
	@Query("SELECT u.userId, ur.userRoleId FROM User u JOIN u.userRoles ur")
	List<Object[]> findAllUserRolesAssociations();

	
	/**
	 * Custom query to fetch all roles for a specific user.
	 * 
	 * @param userId The ID of the user whose roles are to be fetched.
	 * @return List of Object arrays containing user role entities associated with the given user ID.
	 */
	@Query("SELECT ur FROM UserRole ur JOIN ur.users u WHERE u.userId = :userId")
	List<Object[]> getAllRolesForASpecificUser(@Param("userId")int userId);

	
	/**
	 * Custom native query to revoke a user role from a user.
	 * 
	 * @param userRoleId The ID of the user role to be revoked.
	 * @param userId The ID of the user from whom the role is to be revoked.
	 */
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM USERROLES WHERE USERROLEID = :userRoleId AND USERID = :userId", nativeQuery = true)
	void revokeUserRoleFromUser(@Param("userRoleId")int userRoleId,@Param("userId") int userId);
	
	/**
	 * Custom query to fetch all role names for a specific user.
	 * 
	 * @param userId The ID of the user whose role names are to be fetched.
	 * @return List of strings containing role names associated with the given user ID.
	 */
	@Query("SELECT ur.roleName FROM User u JOIN u.userRoles ur where u.userId=:userId")
	List<String> getAllUserRoleNameWithUserId(@Param("userId")int userId);
	
	
	/**
	 * Custom query to fetch all user role IDs.
	 * 
	 * @return Array of integers containing all user role IDs.
	 */
	@Query("Select ur.userRoleId from UserRole ur")
	int[] getAllRoleIds();
}
