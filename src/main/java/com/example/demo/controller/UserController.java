package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticateUserDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.dto.UserUserRoleDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.InvalidUserException;
import com.example.demo.exception.TaskListIsEmptyException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * Handles HTTP POST requests to add a new user.
	 * @param user
	 * @return ResponseEntity containing SuccessResponse object and a HTTP status code(201)
	 * @throws UserAlreadyExistsException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> addANewUser(@RequestBody @Valid User user)
			throws UserAlreadyExistsException {
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS", "User added successfully");
		userService.createANewUser(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Handles HTTP GET requests to fetch all users.
	 * @return ResponseEntity containing a List of User object and a HTTP status code(200)
	 * @throws UserListIsEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() throws UserListIsEmptyException {
		return new ResponseEntity<List<User>>(userService.getListOfAllUsers(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch User id using user name.
	 * @param userName
	 * @return ResponseEntity containing a Integer object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/userName/{userName}")
	public ResponseEntity<Integer> getUserIdByUserName(@PathVariable("userName") String userName) throws UserDoesNotExistException{
		return new ResponseEntity<Integer>(userService.getUserIdByUserName(userName),HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all details of a User using user id.
	 * @param userId
	 * @return ResponseEntity containing a User object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<User> getDetailsOfSpecificUserById(@PathVariable("userId") int userId)
			throws UserDoesNotExistException {
		return new ResponseEntity<User>(userService.getDetailsOfSpecificUserById(userId), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Users with specific email domain.
	 * @param domain
	 * @return ResponseEntity containing a List of User object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/email-domain/{domain}")
	public ResponseEntity<List<User>> getUsersWithSpecificEmailDomain(@PathVariable("domain") String domain)
			throws UserDoesNotExistException {
		return new ResponseEntity<List<User>>(userService.getUsersWithSpecificEmailDomain(domain), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Users with full name.
	 * @param fullName
	 * @return ResponseEntity containing a List of User object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/search/{fullName}")
	public ResponseEntity<List<User>> getUsersByFullName(@PathVariable("fullName") String fullName)
			throws UserDoesNotExistException {
		return new ResponseEntity<List<User>>(userService.getUsersByFullName(fullName), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Users with Most tasks.
	 * @return ResponseEntity containing a List of User object and a HTTP status code(200)
	 * @throws UserListIsEmptyException
	 */
	@GetMapping("/most-tasks")
	public ResponseEntity<List<User>> getUsersWithMostTasks() throws UserListIsEmptyException {
		return new ResponseEntity<List<User>>(userService.getUsersWithMostTasks(), HttpStatus.OK);
	}

	/**
	 * Handles HTTP POST requests to authenticate a User.
	 * @param request
	 * @return ResponseEntity containing a SuccessResponse object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 * @throws InvalidUserException
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<SuccessResponseDto> authenticateUser(@RequestBody AuthenticateUserDto request)
			throws UserDoesNotExistException, InvalidUserException {
		SuccessResponseDto response = new SuccessResponseDto("AUTHSUCCESS", "User is valid");
		userService.authenticateUser(request.getUserName(), request.getPassword());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Handles HTTP PUT requests to update User details by User id.
	 * @param userId
	 * @param updatedUser
	 * @return ResponseEntity containing a SuccessResponse object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@PutMapping("/update/{userId}")
	public ResponseEntity<SuccessResponseDto> updateUserDetailsByUserId(@PathVariable("userId") int userId,
			@RequestBody @Valid User updatedUser) throws UserDoesNotExistException {
		SuccessResponseDto response = new SuccessResponseDto("UPDATESUCCESS", "User updated successfully");
		userService.updateUserDetailsByUserId(userId, updatedUser);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Handles HTTP DELETE requests to delete User details by User id.
	 * @param userId
	 * @return ResponseEntity containing a SuccessResponse object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<SuccessResponseDto> deleteUserByUserId(@PathVariable("userId") int userId)
			throws UserDoesNotExistException {
		SuccessResponseDto response = new SuccessResponseDto("DELETESUCCESS", "User deleted successfullt");
		userService.deleteUserByUserId(userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch UserRoleId with UserName details by UserName.
	 * @param userName
	 * @return ResponseEntity containing a List of Integer object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/login/{userName}")
	public ResponseEntity<List<Integer>> getAllUserRoleIdsByUserName(@PathVariable("userName")String userName) throws UserDoesNotExistException
	{
		return new ResponseEntity<List<Integer>>(userService.getAllUserRoleIdsByUserName(userName), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch All User Roles with UserId.
	 * @param userId
	 * @return ResponseEntity containing a List of UserRole object and a HTTP status code(200)
	 * @throws UserDoesNotExistException
	 */
	@GetMapping("/userRoles/{userId}")
	public ResponseEntity<List<UserRole>> getAllUserRolesWithUserId(@PathVariable("userId")int userId) throws UserDoesNotExistException{
		return new ResponseEntity<List<UserRole>>(userService.getAllUserRolesWithUserId(userId), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch All User  with UserRole.	
	 * @return ResponseEntity containing a List of UserUserRoleDto object and a HTTP status code(200)
	 *  @throws UserListIsEmptyException 
	 *  @throws UserRoleListIsEmptyException
	 */
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserUserRoleDto>> getAllUserWithUserRole() throws UserListIsEmptyException,UserRoleListIsEmptyException {
		return new ResponseEntity<List<UserUserRoleDto>>(userService.getAllUserWithUserRole(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch All UserIds.	
	 * @return ResponseEntity containing a integer Array and a HTTP status code(200)
	 *  @throws UserListIsEmptyException 
	 */
	@GetMapping("/allIds")
	public ResponseEntity<int[]> getAllUserIds()
	{
		return new ResponseEntity<int[]>(userService.getAllUserIds(), HttpStatus.OK);
	}
	
	
	
}
