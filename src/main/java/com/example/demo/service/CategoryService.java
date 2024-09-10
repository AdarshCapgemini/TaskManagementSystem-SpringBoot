package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryAlreadyExistException;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CategoryListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;

public interface CategoryService {
	
	public List<Category> getALL() throws CategoryListIsEmptyException;
	public Category getCategoryByCategoryId(int categoryId) throws CategoryDoesntExistException;
	Category createCategory(Category category) throws CategoryAlreadyExistException;
	Category updateCategoryById( int categoryId, Category categoryDetails) throws CategoryDoesntExistException;
	Category deleteCategory(int id)throws CategoryDoesntExistException;
	List<Object[]> getAllCategoryForTask(int taskId) throws TaskDoesntExistException,CategoryListIsEmptyException;
//	List<Object[]> findCategoriesWithTaskCount();
}
