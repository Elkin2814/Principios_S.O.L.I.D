/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import co.edu.unicauca.openmarket.access.IRepository;

/**
 *
 * @author ideapad330S
 */
public class CategoryService {

    private IRepository repository;

    public CategoryService(IRepository repository) {
        this.repository = repository;
    }

    public boolean saveCategory(String name) {

        Category newCategory = new Category();
        newCategory.setName(name);

        //Validate product
        if (newCategory.getName().isBlank()) {
            return false;
        }

        return repository.save(newCategory);

    }

    public List<Category> findAllCategories() {
        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos = repository.findAll();
        List<Category> listaCategory = listaObjetos.stream()
                .filter(objeto -> objeto instanceof Category)
                .map(objeto -> (Category) objeto)
                .collect(Collectors.toList());
        return listaCategory;
    }

    public Category findCategoryById(Long id) {
        return (Category) repository.findById(id);
    }

    public boolean deleteCategory(Long id) {
        return repository.delete(id);
    }

    public List<Category> findCategoryByName(String name) {
         List<Object> listaObjetos = new ArrayList<>();
         listaObjetos = repository.findByName(name);
                List<Category> listaCategory = listaObjetos.stream()
                .filter(objeto -> objeto instanceof Category)
                .map(objeto -> (Category) objeto)
                .collect(Collectors.toList());
        return listaCategory;
    }

    public boolean editCategory(Long categoryId, Category category) {

        //Validate category
        if (category == null || category.getName().isBlank()) {
            return false;
        }
        return repository.edit(categoryId, category);
    }
}
