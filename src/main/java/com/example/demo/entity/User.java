package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties
public class User {
	@Id
	@Column(name = "USERID")
	private int userId;
 
	@Column(name = "USERNAME")
	@Size(min = 3, max = 15, message = "User name must be between 3 and 15 characters")
	@NotNull(message="User name cannot be null")
	private String userName;
 
	@Column(name = "PASSWORD")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@$#&%]).*$", message = "Password must contain at least 1 uppercase,1 lowercase, and 1 Special character(@,$,#,&,%)")
	@NotNull(message="Password cannot not be null")
	private String password;
 
	@Column(name = "EMAIL")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email is not valid")
	@NotNull(message="Email cannot be null")
	private String email;
 
	@Column(name = "FULLNAME")
	@Size(min = 3, max = 20, message = "Full name must be between 3 and 20 characters")
	@NotNull(message="Full name cannot be null")
	private String fullName;

	// Mapping ONE-MANY With TASK
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Task> tasks;

	// Mapping ONE-MANY With PROJECT
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Project> projects;

	// Mapping ONE-MANY With COMMENTS
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Comment> comments;

	// Mapping MANY_MANY With USERROLE
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private List<UserRole> userRoles;

	// Mapping ONE-MANY With NOTIFICATION
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Notification> notifications;

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public User(int userId, String userName, String password, String email, String fullName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}

	public User() {
		super();
	}

}
