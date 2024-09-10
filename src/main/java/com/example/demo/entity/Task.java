package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TASK")
public class Task {

	@Id
	@Column(name = "TASKID")
	private int taskId;

	@Column(name = "TASKNAME")
	@Size(min = 1, max = 100, message = "Task name must be between 1 and 100 characters")
	@NotNull
	private String taskName;

	@Column(name = "DESCRIPTION", columnDefinition = "TEXT")
	@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;

	@Column(name = "DUEDATE")
	@FutureOrPresent(message = "Due date must be in the present or future")
	private LocalDate dueDate;

	@Column(name = "PRIORITY")
	@Pattern(regexp = "Low|Medium|High", message = "Priority must be Low, Medium, or High")
	private String priority;

	@Column(name = "STATUS")
	@Pattern(regexp = "Pending|In Progress|Completed", message = "Status must be Pending, In Progress, or Completed")
	private String status;

	// Mapping ONE-MANY With COMMENT
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	List<Comment> comments;

	// Mapping ONE-MANY With Attachment
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	List<Attachment> attchments;

	// Mapping MANY-MANY With CATEGORY
	@ManyToMany(mappedBy = "tasks")
	@JsonIgnore
	private List<Category> categories;

	// Mapping MANY-ONE With USER
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	@JsonBackReference
	@JsonIgnore
	private User user;

	// Mapping MANY-ONE With PROJECT
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID")
	@JsonBackReference
	@JsonIgnore
	private Project project;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Attachment> getAttchments() {
		return attchments;
	}

	public void setAttchments(List<Attachment> attchments) {
		this.attchments = attchments;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	// Default constructor
	public Task() {
	}

	// Parameterized constructor
	public Task(int taskId, String taskName, String description, LocalDate dueDate, String priority, String status) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.status = status;

	}

	// Getters and Setters
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", description=" + description + ", dueDate="
				+ dueDate + ", priority=" + priority + ", status=" + status + "]";
	}

}
