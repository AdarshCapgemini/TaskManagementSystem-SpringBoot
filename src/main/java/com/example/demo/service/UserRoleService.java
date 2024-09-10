package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleAlreadyExistsException;
import com.example.demo.exception.UserRoleDoesNotExistException;
import com.example.demo.exception.UserRoleListIsEmptyException;

public interface UserRoleService {

	UserRole createANewUserRole(UserRole userRole) throws UserRoleAlreadyExistsException;

	List<UserRole> getListOfAllUserRoles() throws UserRoleListIsEmptyException;

	UserRole getUserRoleById(int userRoleId) throws UserRoleDoesNotExistException;

	UserRole updateUserRoleDetailsByUserRoleId(int userRoleId, UserRole updatedUserRole)
			throws UserRoleDoesNotExistException;

	UserRole deleteUserRoleByUserRoleId(int userRoleId) throws UserRoleDoesNotExistException;

	void assignRoleToUser(int userId, int userRoleId) throws UserRoleDoesNotExistException, UserDoesNotExistException;

	List<Object[]> findAllUserRolesAssociations() throws UserRoleListIsEmptyException, UserListIsEmptyException;

	List<Object[]> getAllRolesForASpecificUser(int userId)
			throws UserDoesNotExistException, UserRoleListIsEmptyException;

	void revokeUserRoleFromUser(int userRoleId, int userId)
			throws UserDoesNotExistException, UserRoleListIsEmptyException, UserRoleDoesNotExistException;
	
	int[] getAllRoleIds();
}
