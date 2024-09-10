package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CATEGORY")
@JsonIgnoreProperties
public class Category {
	@Id
    @Column(name="CATEGORYID")
    @NotNull(message = "Category ID cannot be null")
    int categoryId;
    
    @Column(name="CATEGORYNAME")
    @NotNull(message = "Category name cannot be blank")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    String categoryName;

	// Mapping MANY-MANY With CATEGORY
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TASKCATEGORY", joinColumns = @JoinColumn(name = "CATEGORYID"), inverseJoinColumns = @JoinColumn(name = "TASKID"))
	@JsonIgnore
	private List<Task> tasks;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category() {
	}

	public Category(int categoryID, String categoryName) {
		super();
		this.categoryId = categoryID;
		this.categoryName = categoryName;
	}

}
