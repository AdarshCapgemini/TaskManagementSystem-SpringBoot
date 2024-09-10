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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssignRoleToUserRequestDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.exception.UserListIsEmptyException;
import com.example.demo.exception.UserRoleDoesNotExistException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/userroles")
@CrossOrigin("http://localhost:4200")
public class UserRolesController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/assign")
	public ResponseEntity<SuccessResponseDto> assignRoleToUser(@RequestBody @Valid AssignRoleToUserRequestDto request) throws UserRoleDoesNotExistException,UserDoesNotExistException {
	    SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS", "Userrole added successfully");
	    userRoleService.assignRoleToUser(request.getUserId(), request.getUserRoleId());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Object[]>> getAllUserRolesAssociation() throws UserListIsEmptyException,UserRoleListIsEmptyException {
		List<Object[]> userRolesAssociations = userRoleService.findAllUserRolesAssociations();
        return new ResponseEntity<List<Object[]>>(userRolesAssociations,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Object[]>> getAllRolesForASpecificUser(@PathVariable("userId")int userId) throws UserDoesNotExistException,UserRoleListIsEmptyException {
		List<Object[]> allRolesForUser = userRoleService.getAllRolesForASpecificUser(userId);
		return new ResponseEntity<List<Object[]>>(allRolesForUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/revoke/{userRoleId}/{userId}")
	public ResponseEntity<SuccessResponseDto> revokeUserRoleFromUser(@PathVariable("userRoleId")int userRoleId,@PathVariable("userId")int userId) throws UserDoesNotExistException,UserRoleListIsEmptyException,UserRoleDoesNotExistException {
		SuccessResponseDto response=new SuccessResponseDto("DELETESUCCESS","Userrole deleted successfully");
		userRoleService.revokeUserRoleFromUser(userRoleId, userId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
