package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryAlreadyExistException;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CategoryListIsEmptyException;
import com.example.demo.exception.TaskDoesntExistException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	TaskRepository taskRepository;
		
	/**
     * Retrieves all categories.
     * @return A list of all Category entities.
     * @throws CategoryListIsEmptyException If no categories are found.
     */
	@Override
	public List<Category> getALL() throws CategoryListIsEmptyException 
	{
		if(categoryRepository.findAll().isEmpty())
		{
			throw new CategoryListIsEmptyException("Category list is empty"); 
		}
		else
		{
			return categoryRepository.findAll();
		}
		
	}
	
	/**
     * Retrieves a category by its ID.
     * @param categoryId The ID of the category to retrieve.
     * @return The Category entity with the specified ID.
     * @throws CategoryDoesntExistException If no category with the given ID is found.
     */
	public Category getCategoryByCategoryId(int categoryId)throws CategoryDoesntExistException 
	{
		if(categoryRepository.findById(categoryId).isEmpty())
		{
			throw new CategoryDoesntExistException("Category doesn't exist");
		}
		return categoryRepository.getCategoryByCategoryId(categoryId);
	}
	
	/**
     * Creates a new category.
     * @param category The Category entity to create.
     * @return The created Category entity.
     * @throws CategoryAlreadyExistException If a category with the same ID already exists.
     */
	public Category createCategory(Category category) throws CategoryAlreadyExistException
	{
		if(categoryRepository.findById(category.getCategoryId()).isPresent())
		{
			throw new CategoryAlreadyExistException("Category already exist");
		}
		else
		{
			return categoryRepository.save(category);

		}
	}
	
	/**
     * Updates an existing category by its ID.
     * @param categoryId The ID of the category to update.
     * @param categoryDetails The Category entity with updated details.
     * @return The updated Category entity.
     * @throws CategoryDoesntExistException If no category with the given ID is found.
     */
	public Category updateCategoryById(int categoryId, Category categoryDetails) throws CategoryDoesntExistException
	{
		if(categoryRepository.findById(categoryId).isEmpty())
		{
			throw new CategoryDoesntExistException("Category doesn't exist");
		}
		else
		{
			return categoryRepository.save(categoryDetails);
		}	
	}
	
	/**
     * Deletes a category by its ID.
     * @param categoryId The ID of the category to delete.
     * @return The deleted Category entity.
     * @throws CategoryDoesntExistException If no category with the given ID is found.
     */
	public Category deleteCategory(int categoryId) throws CategoryDoesntExistException
	{
		if(categoryRepository.findById(categoryId).isEmpty())
		{
			throw new CategoryDoesntExistException("Category doesn't exist");
		}
		else
		{
		Category category=categoryRepository.findById(categoryId).get();
		categoryRepository.deleteById(categoryId);
		return category;
		}
	}
	
	/**
     * Retrieves all categories associated with a specific task ID.
     * @param taskId The ID of the task for which categories are to be retrieved.
     * @return A list of Object arrays, where each array contains category details for the specified task.
     * @throws TaskDoesntExistException If the task with the specified ID does not exist.
     * @throws CategoryListIsEmptyException If no categories are found.
     */
	public List<Object[]> getAllCategoryForTask(int taskId) throws TaskDoesntExistException,CategoryListIsEmptyException
	{
		if(categoryRepository.findAll().isEmpty())
			throw new CategoryListIsEmptyException("Category list is empty");
		else if(taskRepository.findById(taskId).isEmpty())
			throw new TaskDoesntExistException("Task doesn't exist");
		else
		{
			List<Object[]> allCategoryForTask = categoryRepository.getAllCategoryForTask(taskId);
			return allCategoryForTask;
		}
	}
	
//	 Uncomment if needed
//     /**
//      * Retrieves categories along with their task counts.
//      * @return A list of Object arrays, where each array contains category and task count details.
//      */
//	 public  List<Object[]> findCategoriesWithTaskCount(){
//        return categoryRepository.findCategoriesWithTaskCount();
//    }

}
