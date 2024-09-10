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

import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.UserRoleAlreadyExistsException;
import com.example.demo.exception.UserRoleDoesNotExistException;
import com.example.demo.exception.UserRoleListIsEmptyException;
import com.example.demo.service.UserRoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/userrole")
@CrossOrigin("http://localhost:4200")
public class UserRoleController {
	
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createANewUserRole(@RequestBody @Valid UserRole userRole) throws UserRoleAlreadyExistsException
	{
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS","UserRole added successfully");
		userRoleService.createANewUserRole(userRole);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserRole>> getAllUserRoles() throws UserRoleListIsEmptyException
	{
		return new ResponseEntity<List<UserRole>>(userRoleService.getListOfAllUserRoles(),HttpStatus.OK);
	}
	
	@GetMapping("/{userRoleId}")
	public ResponseEntity<UserRole> getUserRoleById(@PathVariable("userRoleId")int userRoleId) throws UserRoleDoesNotExistException
	{
		return new ResponseEntity<UserRole>(userRoleService.getUserRoleById(userRoleId),HttpStatus.OK);
	}
	
	@PutMapping("/update/{userRoleId}")
	public ResponseEntity<SuccessResponseDto> updateUserRoleDetailsByUserId(@PathVariable("userRoleId")int userRoleId,@RequestBody @Valid UserRole updatedUserRole) throws UserRoleDoesNotExistException
	{
		SuccessResponseDto response=new SuccessResponseDto("UPDATESUCCESS","UserRole updated successfully");
		userRoleService.updateUserRoleDetailsByUserRoleId(userRoleId,updatedUserRole);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userRoleId}")
	public ResponseEntity<SuccessResponseDto> deleteUserRoleByUserRoleId(@PathVariable("userRoleId")int userRoleId) throws UserRoleDoesNotExistException
	{
		SuccessResponseDto response=new SuccessResponseDto("DELETESUCCESS","UserRole deleted successfullt");
		userRoleService.deleteUserRoleByUserRoleId(userRoleId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/allRoleIds")
	public ResponseEntity<int[]> getAllUserIds()
	{
		return new ResponseEntity<int[]>(userRoleService.getAllRoleIds(), HttpStatus.OK);
	}
}
