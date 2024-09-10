package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PROJECT")
@JsonIgnoreProperties
public class Project {
	@Id
	@Column(name = "PROJECTID")
	private int projectId;

	@Column(name = "PROJECTNAME")
	@Size(min = 1, max = 100, message = "Project name must be between 1 and 100 characters")
	@NotNull(message="Firstname should not be null")
	private String projectName;

	@Column(name = "DESCRIPTION",columnDefinition="TEXT")
	@Size(max = 500, message = "Description cannot exceed 500 characters")
	@NotNull(message="Description should not be null")
	private String description;

	@Column(name = "STARTDATE")
	@NotNull(message="StartDate should not be null")
	@FutureOrPresent
	private LocalDate startDate;

	@Column(name = "ENDDATE")
	@NotNull(message="EndDate should not be null")
	@FutureOrPresent
	private LocalDate endDate;
	
	// Mapping ONE-MANY With TASK
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Task> tasks;

	// Mapping MANY-ONE With USER
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	@JsonBackReference
	@JsonIgnore
	private User user;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Project() {
	}

	public Project(int projectId, String projectName, String description, LocalDate startDate, LocalDate endDate) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;

	}

}
