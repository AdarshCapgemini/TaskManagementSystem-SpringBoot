package com.example.demo.dto;

import java.util.List;

public class AssociateTaskWithCategoryDto {
	private int taskId;
	private List<Integer> categoryId;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public List<Integer> getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(List<Integer> categoryId) {
		this.categoryId = categoryId;
	}

}