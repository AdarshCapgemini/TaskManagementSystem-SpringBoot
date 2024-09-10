package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryAlreadyExistException;
import com.example.demo.exception.CategoryDoesntExistException;
import com.example.demo.exception.CategoryListIsEmptyException;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * Handles HTTP GET requests to fetch all categories.
	 * @return ResponseEntity containing List of Category object and an HTTP status code(200)
	 * @throws CategoryListIsEmptyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Category>> getALL() throws CategoryListIsEmptyException
	{
		return new ResponseEntity<List<Category>>(categoryService.getALL(),HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP GET requests to fetch Category by category id.
	 * @param id
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws CategoryDoesntExistException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") int id) throws CategoryDoesntExistException
	{
		return new ResponseEntity<Category>(categoryService.getCategoryByCategoryId(id),HttpStatus.OK);
	}
	
	/**
	 * Handles HTTP POST requests to create a new Category object.
	 * @param category
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws CategoryAlreadyExistsException
	 */
	@PostMapping("/post")
	public ResponseEntity<SuccessResponseDto> createCategory(@RequestBody @Valid Category category) throws CategoryAlreadyExistException
	{
		SuccessResponseDto response = new SuccessResponseDto("POSTSUCCESS","Category added successsfully");
		categoryService.createCategory(category);
		return new ResponseEntity<SuccessResponseDto>(response,HttpStatus.OK);
	}
	
	@PostMapping("/update/{categoryId}")
	public ResponseEntity<SuccessResponseDto> updateCategory(@PathVariable int categoryId,@RequestBody @Valid Category categoryDetails) throws CategoryDoesntExistException
	{
		SuccessResponseDto response = new SuccessResponseDto("UPDATESUCCESS","Category uppdated successsfully");
		categoryService.updateCategoryById(categoryId,categoryDetails);
		return new ResponseEntity<SuccessResponseDto>(response,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<SuccessResponseDto> deleteCategory(@PathVariable int categoryId) throws CategoryDoesntExistException
	{
		SuccessResponseDto response = new SuccessResponseDto("DELETESUCCESS","Category deleted successsfully");
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<SuccessResponseDto>(response,HttpStatus.OK);	
	}
	
	

}
