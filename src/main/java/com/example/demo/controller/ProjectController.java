package com.example.demo.controller;

import java.time.LocalDate;
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

import com.example.demo.dto.ProjectUserDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.Project;
import com.example.demo.exception.NotificationDoesntExistException;
import com.example.demo.exception.ProjectAlreadyExistsException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.ProjectListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("http://localhost:4200")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	/**
	 * Handles HTTP GET requests to fetch all Projects.
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Project>> getAllProjects() throws ProjectListIsEmptyException {
		return new ResponseEntity<List<Project>>(projectService.getAllProjects(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch all Project object along with User id.
	 * @return ResponseEntity containing a List of ProjectUserDto object and an HTTP status code(200)
	 * @throws ProjectDoesNotExistException
	 */
	@GetMapping("/allWithUserId")
	public ResponseEntity<List<ProjectUserDto>> getAllProjectsWithUserId() throws ProjectDoesNotExistException{
		return new ResponseEntity<List<ProjectUserDto>>(projectService.getAllProjectsByUserId(), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP POST requests to create Project object.
	 * @param projectUserDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws ProjectAlreadyExistException
	 * @throws UserDoesNotExistException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createProject(@RequestBody @Valid ProjectUserDto projectUserDto)
			throws ProjectAlreadyExistsException, UserDoesNotExistException {

		projectService.createProject(projectUserDto);
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS", "Project added successfully");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/**
	 * Handles HTTP GET requests to fetch Notification object by user id.
	 * @param projectId
	 * @return ResponseEntity containing a ProjectUserDto object and an HTTP status code(200)
	 * @throws ProjectDoesNotExistException
	 */
	@GetMapping("/project/{projectId}")
	public ResponseEntity<ProjectUserDto> getProjectByProjectId(@PathVariable("projectId")int projectId) throws ProjectDoesNotExistException {
		return new ResponseEntity<ProjectUserDto>(projectService.getProjectByProjectId(projectId), HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch Project object by user id.
	 * @param userId
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/byuserId/{userId}")
	public ResponseEntity<List<Project>> getProjectByUserId(@PathVariable("userId") int userId) throws ProjectListIsEmptyException {

		return new ResponseEntity<List<Project>>(projectService.getProjectByUserId(userId), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch Ongoing projects.
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/ongoing")
	public ResponseEntity<List<Project>> getCurrentlyOngoingProjects() throws ProjectListIsEmptyException {

		return new ResponseEntity<List<Project>>(projectService.getCurrentlyOngoingProjects(), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Project object by their status.
	 * @param status
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Project>> getProjectsByStatus(@PathVariable("status") String status)
			throws ProjectListIsEmptyException {
		return new ResponseEntity<List<Project>>(projectService.getProjectsByStatus(status), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch Projects with "High" priority.
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/{high-priority-tasks}")
	public ResponseEntity<List<Project>> getProjectsWithHighPriorityTasks() throws ProjectListIsEmptyException {
		return new ResponseEntity<List<Project>>(projectService.getProjectsWithHighPriorityTasks(), HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to fetch all Projects in specific date range.
	 * @param startDate
	 * @param endDate
	 * @return ResponseEntity containing a List of Project object and an HTTP status code(200)
	 * @throws ProjectListIsEmptyException
	 */
	@GetMapping("/date-range/{startDate}/{endDate}")
	public ResponseEntity<List<Project>> getProjectsInSpecificDateRange(@PathVariable("startDate") LocalDate startDate,
			@PathVariable("endDate") LocalDate endDate) throws ProjectListIsEmptyException {
		List<Project> projects = projectService.getProjectsInSpecificDateRange(startDate, endDate);
		if (projects.isEmpty()) {
			throw new ProjectListIsEmptyException("Project List is Empty");
		}
		return new ResponseEntity<List<Project>>(projectService.getProjectsInSpecificDateRange(startDate, endDate),
				HttpStatus.OK);
	}

	// update mapping by project id
	@PutMapping("/update/{projectId}")
	public ResponseEntity<SuccessResponseDto> updateProjectByProjectId(@PathVariable("projectId") int projectId,
			@RequestBody ProjectUserDto projectUserDto) throws ProjectDoesNotExistException, UserDoesNotExistException {
		SuccessResponseDto response = new SuccessResponseDto("UPDATESUCCESS", "Project updated successfully");
		projectService.updateProjectByProjectId(projectId, projectUserDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// delete Mapping by project id
	@DeleteMapping("/delete/{projectId}")
	public ResponseEntity<SuccessResponseDto> deleteProjectByProjectId(@PathVariable("projectId") int projectId)
			throws ProjectDoesNotExistException {
		SuccessResponseDto response = new SuccessResponseDto("DELETESUCCESS", "Project deleted successfully");
		projectService.deleteProjectByProjectId(projectId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

////get projects by rolename from userrole table
//  @GetMapping("/user-role/{roleName}")
//  public ResponseEntity<List<Project>> getProjectsByRoleName(@PathVariable String roleName)
//          throws ProjectListIsEmptyException {
//      return new ResponseEntity<List<Project>>(projectService.getProjectsByRoleName(roleName), HttpStatus.OK);
//
//  }

}
