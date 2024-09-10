package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ProjectUserDto;
import com.example.demo.entity.Project;
import com.example.demo.exception.ProjectAlreadyExistsException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.ProjectListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;

public interface ProjectService {

	List<Project> getAllProjects() throws ProjectListIsEmptyException;
	List<ProjectUserDto> getAllProjectsByUserId() throws ProjectDoesNotExistException;

	Project createProject(ProjectUserDto projectUserDto)
			throws ProjectAlreadyExistsException, UserDoesNotExistException;
	ProjectUserDto getProjectByProjectId(int projectId) throws ProjectDoesNotExistException;
	
	List<Project> getProjectByUserId(int userId)throws ProjectListIsEmptyException;

	List<Project> getCurrentlyOngoingProjects() throws ProjectListIsEmptyException;

	Project updateProjectByProjectId(int projectId,ProjectUserDto projectUserDto) throws ProjectDoesNotExistException,UserDoesNotExistException;

	
	//   List<Project> getProjectsByRoleName(String roleName) throws ProjectListIsEmptyException;
	List<Project> getProjectsByStatus(String status);

	List<Project> getProjectsInSpecificDateRange(LocalDate startDate, LocalDate endDate)
			throws ProjectListIsEmptyException;

	List<Project> getProjectsWithHighPriorityTasks() throws ProjectListIsEmptyException;

	Project deleteProjectByProjectId(int projectId) throws ProjectDoesNotExistException;

}
