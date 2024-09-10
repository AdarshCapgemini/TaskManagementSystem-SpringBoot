package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectUserDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.exception.ProjectAlreadyExistsException;
import com.example.demo.exception.ProjectDoesNotExistException;
import com.example.demo.exception.ProjectListIsEmptyException;
import com.example.demo.exception.UserDoesNotExistException;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

@Service
public  class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
     * Retrieves all projects.
     * @return A list of all Project entities.
     * @throws ProjectListIsEmptyException If no projects are found.
     */
	@Override
	public List<Project> getAllProjects() throws ProjectListIsEmptyException {
		return projectRepository.findAll();

	}
	
	/**
     * Retrieves all projects by user ID.
     * @return A list of ProjectUserDto containing project details associated with users.
     * @throws ProjectDoesNotExistException If no projects are found for the user.
     */
	public List<ProjectUserDto> getAllProjectsByUserId()throws ProjectDoesNotExistException{
		return projectRepository.getAllProjectsByUserId();
 
	}

	/**
     * Creates a new project.
     * @param projectUserDto Data transfer object containing project details and user ID.
     * @return The created Project entity.
     * @throws ProjectAlreadyExistsException If a project with the same ID already exists.
     * @throws UserDoesNotExistException If the user associated with the project does not exist.
     */
	@Override
	public Project createProject(ProjectUserDto projectUserDto) throws ProjectAlreadyExistsException, UserDoesNotExistException {
		Project project= new Project();
		project.setProjectId(projectUserDto.getProjectId());
		project.setProjectName(projectUserDto.getProjectName());
		project.setDescription(projectUserDto.getDescription());
		project.setStartDate(projectUserDto.getStartDate());
		project.setEndDate(projectUserDto.getEndDate());
		
		if (projectRepository.findById(project.getProjectId()).isPresent())
			throw new ProjectAlreadyExistsException("Project already exist");
		else {
			User user = userRepository.findById(projectUserDto.getUserId())
					.orElseThrow(()-> new UserDoesNotExistException("User doesn't exist"));
			
			project.setUser(user);
			
			return projectRepository.save(project);
		}
	}
	
	/**
     * Retrieves a project by its ID.
     * @param projectId The ID of the project to retrieve.
     * @return A ProjectUserDto containing the details of the project.
     * @throws ProjectDoesNotExistException If no project with the given ID is found.
     */
	@Override
	public ProjectUserDto getProjectByProjectId(int projectId) throws ProjectDoesNotExistException {

    	if(projectRepository.findById(projectId).isEmpty())

    		throw new ProjectDoesNotExistException("Project doesn't exist");

    	else

    		return projectRepository.getProjectByProjectId(projectId);

    }

	/**
     * Retrieves projects associated with a specific user ID.
     * @param userId The ID of the user whose projects are to be retrieved.
     * @return A list of Project entities associated with the specified user ID.
     * @throws ProjectListIsEmptyException If no projects are found for the user.
     */
	@Override
	public List<Project> getProjectByUserId(int userId) throws ProjectListIsEmptyException {
		if (projectRepository.findAll().isEmpty())
			throw new ProjectListIsEmptyException("Project list is empty");
		else
			return projectRepository.getProjectByUserId(userId);
	}

	/**
     * Retrieves currently ongoing projects.
     * @return A list of currently ongoing Project entities.
     * @throws ProjectListIsEmptyException If no ongoing projects are found.
     */
	@Override
	public List<Project> getCurrentlyOngoingProjects() throws ProjectListIsEmptyException {
		if (projectRepository.findAll().isEmpty())
			throw new ProjectListIsEmptyException("Project list is empty");
		else
			return projectRepository.findCurrentlyOngoingProjects();
	}
	
	/**
     * Retrieves projects by their status.
     * @param status The status of the projects to retrieve.
     * @return A list of Project entities with the specified status.
     */
    @Override
    public List<Project> getProjectsByStatus(String status) {
         return projectRepository.getProjectsByStatus(status);
     }
    
    /**
     * Retrieves projects with high-priority tasks.
     * @return A list of Project entities with high-priority tasks.
     * @throws ProjectListIsEmptyException If no projects with high-priority tasks are found.
     */
     @Override
     public List<Project> getProjectsWithHighPriorityTasks() throws ProjectListIsEmptyException {
         if(projectRepository.findAll()== null)
            throw new ProjectListIsEmptyException("Project list is empty");
         else
             return projectRepository.getProjectsWithHighPriorityTasks();
    }
    
     /**
      * Retrieves projects within a specific date range.
      * @param startDate The start date of the date range.
      * @param endDate The end date of the date range.
      * @return A list of Project entities within the specified date range.
      * @throws ProjectListIsEmptyException If no projects are found within the date range.
      */
      @Override
      public List<Project> getProjectsInSpecificDateRange(LocalDate startDate, LocalDate endDate) throws ProjectListIsEmptyException {
            if(projectRepository.findAll().isEmpty())
                throw new ProjectListIsEmptyException("Project list is empty");
            else
                return projectRepository.getProjectsInSpecificDateRange(startDate, endDate);
        }
    
      /**
       * Updates an existing project by its ID.
       * @param projectId The ID of the project to update.
       * @param projectUserDto Data transfer object containing updated project details and user ID.
       * @return The updated Project entity.
       * @throws ProjectDoesNotExistException If no project with the given ID is found.
       * @throws UserDoesNotExistException If the user associated with the project does not exist.
       */
        @Override
        public Project updateProjectByProjectId(int projectId,ProjectUserDto projectUserDto) throws ProjectDoesNotExistException,UserDoesNotExistException {
        	Project project= new Project();
    		project.setProjectId(projectId);
    		project.setProjectName(projectUserDto.getProjectName());
    		project.setDescription(projectUserDto.getDescription());
    		project.setStartDate(projectUserDto.getStartDate());
    		project.setEndDate(projectUserDto.getEndDate());
    		
    		if (!projectRepository.findById(project.getProjectId()).isPresent())
    			throw new ProjectDoesNotExistException("Project doesn't exist");
    		else {
    			User user = userRepository.findById(projectUserDto.getUserId())
    					.orElseThrow(()-> new UserDoesNotExistException("User doesn't exist"));
    			
    			project.setUser(user);
    			
    			return projectRepository.save(project);
    		}
        }
        
        /**
         * Deletes a project by its ID.
         * @param projectId The ID of the project to delete.
         * @return The deleted Project entity.
         * @throws ProjectDoesNotExistException If no project with the given ID is found.
         */
        @Override
        public Project deleteProjectByProjectId(int projectId) throws ProjectDoesNotExistException {
            if(projectRepository.findById(projectId).isEmpty())
                throw new ProjectDoesNotExistException("Project doesn't exist");
            else {
                Project project=projectRepository.findById(projectId).get();
                projectRepository.deleteById(projectId);
                return project;
            }
            
        }

////GET ROLENAME
//  @Override
//  public List<Project> getProjectsByRoleName(String roleName) throws ProjectListIsEmptyException {
//      if(projectRepository.findAll().isEmpty())
//          throw new ProjectListIsEmptyException("Project list is empty");
//      else
//          return projectRepository.getProjectsByRoleName(roleName);
//  }


}
