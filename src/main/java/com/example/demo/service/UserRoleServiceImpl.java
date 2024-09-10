package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleAlreadyExistsException;
import com.example.demo.exception.UserRoleDoesNotExistException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserRepository userRepository;

	/**
     * Creates a new user role if it does not already exist.
     * @param userRole The UserRole entity to create.
     * @return The created UserRole entity.
     * @throws UserRoleAlreadyExistsException If a user role with the same ID already exists.
     */
	public UserRole createANewUserRole(UserRole userRole) throws UserRoleAlreadyExistsException {
		if (userRoleRepository.findById(userRole.getUserRoleId()).isPresent())
			throw new UserRoleAlreadyExistsException("UserRole already exist");
		else
			return userRoleRepository.save(userRole);
	}

	/**
     * Retrieves a list of all user roles.
     * @return A list of all UserRole entities.
     * @throws UserRoleListIsEmptyException If no user roles are found.
     */
	public List<UserRole> getListOfAllUserRoles() throws UserRoleListIsEmptyException {
		if (userRoleRepository.findAll().isEmpty())
			throw new UserRoleListIsEmptyException("UserRole list is empty");
		else
			return userRoleRepository.findAll();
	}

	/**
     * Retrieves a user role by its ID.
     * @param userRoleId The ID of the user role to retrieve.
     * @return The UserRole entity with the specified ID.
     * @throws UserRoleDoesNotExistException If no user role with the given ID is found.
     */
	public UserRole getUserRoleById(int userRoleId) throws UserRoleDoesNotExistException {
		if (userRoleRepository.findById(userRoleId).isEmpty())
			throw new UserRoleDoesNotExistException("UserRole doesn't exist");
		else
			return userRoleRepository.getUserRoleById(userRoleId);
	}

	/**
     * Updates the details of an existing user role.
     * @param userRoleId The ID of the user role to update.
     * @param updatedUserRole The UserRole entity containing updated details.
     * @return The updated UserRole entity.
     * @throws UserRoleDoesNotExistException If no user role with the given ID is found.
     */
	public UserRole updateUserRoleDetailsByUserRoleId(int userRoleId, UserRole updatedUserRole)
			throws UserRoleDoesNotExistException {
		if (userRoleRepository.findById(userRoleId).isEmpty())
			throw new UserRoleDoesNotExistException("UserRole doesn't exist");
		else {
			UserRole userRole = userRoleRepository.findById(userRoleId).get();
			userRole = updatedUserRole;
			return userRoleRepository.save(userRole);
		}
	}

	/**
     * Deletes a user role by its ID.
     * @param userRoleId The ID of the user role to delete.
     * @return The deleted UserRole entity.
     * @throws UserRoleDoesNotExistException If no user role with the given ID is found.
     */
	public UserRole deleteUserRoleByUserRoleId(int userRoleId) throws UserRoleDoesNotExistException {
		if (userRoleRepository.findById(userRoleId).isEmpty())
			throw new UserRoleDoesNotExistException("UserRole doesn't exist");
		else {
			UserRole userRole = userRoleRepository.findById(userRoleId).get();
			userRoleRepository.deleteById(userRoleId);
			;
			return userRole;
		}
	}

	/**
     * Assigns a role to a user.
     * @param userId The ID of the user to whom the role is to be assigned.
     * @param userRoleId The ID of the role to assign to the user.
     * @throws UserRoleDoesNotExistException If the role does not exist.
     * @throws UserDoesNotExistException If the user does not exist.
     */
	public void assignRoleToUser(int userId, int userRoleId)
			throws UserRoleDoesNotExistException, UserDoesNotExistException {
		if (userRoleRepository.findById(userRoleId).isEmpty())
			throw new UserRoleDoesNotExistException("Userrole doesn't exist");
		else if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			userRoleRepository.assignUserRoleToUser(userId, userRoleId);
	}

	/**
     * Retrieves all user-role associations.
     * @return A list of arrays representing user-role associations.
     * @throws UserRoleListIsEmptyException If no user roles are found.
     * @throws UserListIsEmptyException If no users are found.
     */
	public List<Object[]> findAllUserRolesAssociations() throws UserRoleListIsEmptyException, UserListIsEmptyException {
		if (userRoleRepository.findAll().isEmpty())
			throw new UserRoleListIsEmptyException("Userrole list is empty");
		else if (userRepository.findAll().isEmpty())
			throw new UserListIsEmptyException("User list is empty");
		else {
			List<Object[]> userRolesAssociations = userRoleRepository.findAllUserRolesAssociations();
			return userRolesAssociations;
		}
	}

	/**
     * Retrieves all roles for a specific user by user ID.
     * @param userId The ID of the user whose roles are to be retrieved.
     * @return A list of arrays representing roles assigned to the specified user.
     * @throws UserDoesNotExistException If the user does not exist.
     * @throws UserRoleListIsEmptyException If no roles are found.
     */
	public List<Object[]> getAllRolesForASpecificUser(int userId)
			throws UserDoesNotExistException, UserRoleListIsEmptyException {
		if (userRoleRepository.findAll().isEmpty())
			throw new UserRoleListIsEmptyException("Userrole list is empty");
		else if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else {
			List<Object[]> allRolesForUser = userRoleRepository.getAllRolesForASpecificUser(userId);
			return allRolesForUser;
		}
	}

	/**
     * Revokes a role from a user.
     * @param userRoleId The ID of the role to revoke.
     * @param userId The ID of the user from whom the role is to be revoked.
     * @throws UserRoleDoesNotExistException If the role does not exist.
     * @throws UserDoesNotExistException If the user does not exist.
     * @throws UserRoleListIsEmptyException If no user roles are found.
     */
	public void revokeUserRoleFromUser(int userRoleId, int userId)
			throws UserDoesNotExistException, UserRoleListIsEmptyException, UserRoleDoesNotExistException {
		if (userRoleRepository.findAll().isEmpty())
			throw new UserRoleListIsEmptyException("Userrole list is empty");
		else if (userRoleRepository.findById(userRoleId).isEmpty())
			throw new UserRoleDoesNotExistException("Userrole does't exist");
		else if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			userRoleRepository.revokeUserRoleFromUser(userRoleId, userId);
	}
	
	/**
     * Retrieves all role IDs.
     * @return An array of all role IDs.
     */
	@Override
	public int[] getAllRoleIds() {
		return userRoleRepository.getAllRoleIds();
	}

}
