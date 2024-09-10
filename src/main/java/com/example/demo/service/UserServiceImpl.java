package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserUserRoleDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	/**
     * Creates a new user if it does not already exist.
     * @param user The User entity to create.
     * @return The created User entity.
     * @throws UserAlreadyExistsException If a user with the same ID already exists.
     */
	public User createANewUser(User user) throws UserAlreadyExistsException {
		if (userRepository.findById(user.getUserId()).isPresent())
			throw new UserAlreadyExistsException("User already exist");
		else
			return userRepository.save(user);
	}

	/**
     * Retrieves a list of all users.
     * @return A list of all User entities.
     * @throws UserListIsEmptyException If no users are found.
     */
	public List<User> getListOfAllUsers() throws UserListIsEmptyException {
		if (userRepository.findAll().isEmpty())
			throw new UserListIsEmptyException("User list is empty");
		else
			return userRepository.findAll();
	}

	/**
     * Retrieves details of a specific user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return The User entity with the specified ID.
     * @throws UserDoesNotExistException If no user with the given ID is found.
     */
	public User getDetailsOfSpecificUserById(int userId) throws UserDoesNotExistException {
		if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getDetailsOfSpecificUserById(userId);
	}

	 /**
     * Retrieves a list of users with a specific email domain.
     * @param domain The email domain to search for.
     * @return A list of User entities with the specified email domain.
     * @throws UserDoesNotExistException If no users with the given email domain are found.
     */
	public List<User> getUsersWithSpecificEmailDomain(String domain) throws UserDoesNotExistException {
		if (userRepository.getUsersWithSpecificEmailDomain(domain).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getUsersWithSpecificEmailDomain(domain);
	}

	/**
     * Retrieves a list of users with a specific full name.
     * @param fullName The full name to search for.
     * @return A list of User entities with the specified full name.
     * @throws UserDoesNotExistException If no users with the given full name are found.
     */
	public List<User> getUsersByFullName(String fullName) throws UserDoesNotExistException {
		if (userRepository.getUsersByFullName(fullName).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getUsersByFullName(fullName);
	}

	 /**
     * Retrieves a list of users with the most tasks.
     * @return A list of User entities with the most tasks.
     * @throws UserListIsEmptyException If no users are found.
     */
	public List<User> getUsersWithMostTasks() throws UserListIsEmptyException {
		if (userRepository.findAll().isEmpty())
			throw new UserListIsEmptyException("User list is empty");
		else
			return userRepository.getUsersWithMostTasks();
	}

	/**
     * Updates the details of an existing user.
     * @param userId The ID of the user to update.
     * @param updatedUser The User entity containing updated details.
     * @return The updated User entity.
     * @throws UserDoesNotExistException If no user with the given ID is found.
     */
	public User updateUserDetailsByUserId(int userId, User updatedUser) throws UserDoesNotExistException {
		if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else {
			User user = userRepository.findById(userId).get();
			user = updatedUser;
			return userRepository.save(user);
		}
	}

	/**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     * @throws UserDoesNotExistException If no user with the given ID is found.
     */
	public void deleteUserByUserId(int userId) throws UserDoesNotExistException {
		if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else {
			User user = userRepository.findById(userId).get();
			userRepository.deleteById(userId);
		}
	}

	/**
     * Retrieves a list of users with completed tasks.
     * @return A list of User entities with completed tasks.
     * @throws UserListIsEmptyException If no users are found.
     */
	public List<User> getUserWithCompletedTasks() throws UserListIsEmptyException {
		if (userRepository.findAll().isEmpty())
			throw new UserListIsEmptyException("User list is empty");
		else
			return userRepository.getUserWithCompletedTasks();
	}

	/**
     * Authenticates a user based on username and password.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @return True if authentication is successful, otherwise throws an exception.
     * @throws UserDoesNotExistException If the user does not exist.
     * @throws InvalidUserException If the password is incorrect.
     */
	public boolean authenticateUser(String userName, String password)
			throws UserDoesNotExistException, InvalidUserException {
		if (userRepository.findByUserName(userName) == null)
			throw new UserDoesNotExistException("User doesn't exist");
		else if (!userRepository.findByUserName(userName).getPassword().equals(password))
			throw new InvalidUserException("Invalid password");
		else
			return true;
	}

	/**
     * Retrieves the user ID for a given username.
     * @param userName The username of the user.
     * @return The ID of the user with the specified username.
     * @throws UserDoesNotExistException If the user does not exist.
     */
	@Override
	public int getUserIdByUserName(String userName) throws UserDoesNotExistException {
		if (userRepository.findByUserName(userName) == null)
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getUserIdByUserName(userName);
	}

	/**
     * Retrieves all user role IDs associated with a given username.
     * @param userName The username of the user.
     * @return A list of role IDs associated with the specified username.
     * @throws UserDoesNotExistException If the user does not exist.
     */
	public List<Integer> getAllUserRoleIdsByUserName(String userName) throws UserDoesNotExistException {
		if (userRepository.findByUserName(userName) == null)
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getAllUserRoleIdsByUserName(userName);
	}

	 /**
     * Retrieves all user roles associated with a specific user ID.
     * @param userId The ID of the user whose roles are to be retrieved.
     * @return A list of UserRole entities associated with the specified user ID.
     * @throws UserDoesNotExistException If no user with the given ID is found.
     */
	public List<UserRole> getAllUserRolesWithUserId(int userId) throws UserDoesNotExistException {
		if (userRepository.findById(userId).isEmpty())
			throw new UserDoesNotExistException("User doesn't exist");
		else
			return userRepository.getAllUserRolesWithUserId(userId);
	}

	/**
     * Retrieves a list of all users along with their roles.
     * @return A list of UserUserRoleDto objects containing user and role information.
     * @throws UserListIsEmptyException If no users are found.
     * @throws UserRoleListIsEmptyException If no user roles are found.
     */
	public List<UserUserRoleDto> getAllUserWithUserRole()
			throws UserListIsEmptyException, UserRoleListIsEmptyException {
		if (userRepository.findAll().isEmpty())
			throw new UserListIsEmptyException("User list is empty");
		else if (userRoleRepository.findAll().isEmpty())
			throw new UserRoleListIsEmptyException("User Role list is empty");
		else {
			List<Object[]> results = userRepository.getAllUserWithUserRole();
			List<UserUserRoleDto> userUserRoleDtos = new ArrayList<>();

			for (Object[] result : results) {
				int userId = (int) result[0];
				String fullName = (String) result[1];
				String email = (String) result[2];
				List<String> roleName = userRoleRepository.getAllUserRoleNameWithUserId(userId);
				UserUserRoleDto dto = new UserUserRoleDto(userId, fullName, email, roleName);
				userUserRoleDtos.add(dto);
			}
			return userUserRoleDtos;
		}
	}

	/**
     * Retrieves all user IDs.
     * @return An array of all user IDs.
     * @throws UserListIsEmptyException If no users are found.
     */
	public int[] getAllUserIds() {
		return userRepository.getAllUserIds();
	}

}
