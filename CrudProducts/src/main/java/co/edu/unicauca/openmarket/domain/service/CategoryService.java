/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.domain.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ideapad330S
 */
public class CategoryService {
    
    private ICategoryRepository repository;

    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }
    
    
       public boolean saveCategory(String name) {
        
        Category newCategory = new Category();
        newCategory.setName(name);
        
        //Validate product
        if (newCategory.getName().isBlank() ) {
            return false;
        }

        return repository.save(newCategory);

    }

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories = repository.findAll();

        return categories;
    }
    
    public Category findCategoryById(Long id){
        return repository.findById(id);
    }
    
    public boolean deleteCategory(Long id){
        return repository.delete(id);
    }
    
    public List<Category> findCategoryByName(String name){
        return repository.findByName(name);
    }

    public boolean editCategory(Long categoryId, Category category) {
        
        //Validate category
        if (category== null || category.getName().isBlank() ) {
            return false;
        }
        return repository.edit(categoryId, category);
    }
}
