package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProjectUserDto;
import com.example.demo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	
	/**
	 * Retrieves a list of projects that are currently ongoing.
	 *
	 * @return a list of Project objects that are ongoing as of the current date
	 */
	@Query("select p from Project p where p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE")
	List<Project> findCurrentlyOngoingProjects();
	
	
	/**
	 * Custom query to fetch project details along with user information.
	 * 
	 * @return List of ProjectUserDto containing project and user details.
	 */
	@Query("SELECT new com.example.demo.dto.ProjectUserDto(p.projectId, p.projectName, p.description, p.startDate, p.endDate, u.userId) " +
		       "FROM Project p JOIN p.user u " +
		       "ORDER BY p.projectId")
	List<ProjectUserDto> getAllProjectsByUserId();

//   @Query("SELECT p FROM Project p JOIN UserRoles ur ON p.User = ur.User WHERE ur.UserRole.roleName = :roleName")
//   @Query("SELECT p FROM Project p JOIN p.user u JOIN u.UserRoles ur JOIN ur.UserRole r WHERE r.roleName = :roleName")
//   List<Project> getProjectsByRoleName(String roleName);
	
	/**
	 * Custom query to fetch projects for a specific user.
	 * 
	 * @param userId The ID of the user whose projects are to be fetched.
	 * @return List of Project entities associated with the given user ID.
	 */
	@Query("select p from Project p where p.user.userId = :userId")
	List<Project> getProjectByUserId(@Param("userId")int userId);
	
	
	/**
	 * Custom query to fetch project details along with user information for a specific project ID.
	 * 
	 * @param projectId The ID of the project to be fetched.
	 * @return ProjectUserDto containing project and user details for the given project ID.
	 */
	@Query("SELECT new com.example.demo.dto.ProjectUserDto(p.projectId, p.projectName, p.description, p.startDate, p.endDate, u.userId) " +
	           "FROM Project p JOIN p.user u WHERE p.projectId = :projectId")
	ProjectUserDto getProjectByProjectId(@Param("projectId")int projectId);

	/**
	 * Custom query to fetch projects that have tasks with a specific status.
	 * 
	 * @param status The status of the tasks to filter projects by.
	 * @return List of Project entities that have tasks with the given status.
	 */
	
	@Query("SELECT p FROM Project p WHERE EXISTS (SELECT t FROM Task t WHERE t.project = p AND t.status = :status)")
	List<Project> getProjectsByStatus(@Param("status")String status);
	
	

	/**
	 * Custom query to fetch projects that fall within a specific date range.
	 * 
	 * @param startDate The start date of the range.
	 * @param endDate The end date of the range.
	 * @return List of Project entities that start on or after the start date and end on or before the end date.
	 */
	@Query("SELECT p FROM Project p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
	List<Project> getProjectsInSpecificDateRange(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);


	/**
	 * Custom query to fetch projects that have high-priority tasks.
	 * 
	 * @return List of Project entities that have tasks with 'HIGH' priority.
	 */
	@Query("SELECT p FROM Project p WHERE EXISTS(SELECT t FROM Task t WHERE t.project = p AND t.priority = 'HIGH')")
	List<Project> getProjectsWithHighPriorityTasks();

}
