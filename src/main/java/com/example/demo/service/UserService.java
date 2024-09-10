package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserUserRoleDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleListIsEmptyException;

public interface UserService {

	User createANewUser(User user) throws UserAlreadyExistsException;

	List<User> getListOfAllUsers() throws UserListIsEmptyException;

	User getDetailsOfSpecificUserById(int userId) throws UserDoesNotExistException;

	List<User> getUsersWithSpecificEmailDomain(String domain) throws UserDoesNotExistException;

	List<User> getUsersByFullName(String fullName) throws UserDoesNotExistException;

	List<User> getUsersWithMostTasks() throws UserListIsEmptyException;
	
	int getUserIdByUserName( String userName) throws UserDoesNotExistException;

	User updateUserDetailsByUserId(int userId, User updatedUser) throws UserDoesNotExistException;

	void deleteUserByUserId(int userId) throws UserDoesNotExistException;

	boolean authenticateUser(String userName, String password) throws UserDoesNotExistException, InvalidUserException;
	
	List<Integer> getAllUserRoleIdsByUserName(String userName) throws UserDoesNotExistException;
	
	List<UserRole> getAllUserRolesWithUserId(int userId) throws UserDoesNotExistException;
	
	int[] getAllUserIds();
	
	List<UserUserRoleDto> getAllUserWithUserRole() throws UserListIsEmptyException,UserRoleListIsEmptyException;
}
