package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	/**
	 *  Retrieves a category by its ID
	 * 
	 * @param categoryId the ID of the category to be retrieved
	 * @return the Category object with the specified ID
	 */
	@Query("Select c from Category c WHERE c.categoryId=:categoryId")
	Category getCategoryByCategoryId(@Param("categoryId")int categoryId);
	
	
	/**
	 * 
  	 * Retrieves all categories associated with a specific task ID.
     *
     * @param taskId the ID of the task whose categories are to be retrieved
     * @return a list of Object arrays, each containing the Category objects associated with the specified task ID
	 */
	@Query("SELECT c FROM Category c JOIN c.tasks t WHERE t.taskId = :taskId")
	List<Object[]> getAllCategoryForTask(@Param("taskId")int taskId);

//    @Query("\"SELECT c.id, c.name, COUNT(t.id) \" +\r\n"
//            + "                      \"FROM Category c \" +\r\n"
//            + "                      \"LEFT JOIN c.tasks t \" +\r\n"
//            + "                      \"GROUP BY c.id, c.name\";")
//    List<Object[]> findCategoriesWithTaskCount();
}
