/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.CategoryRepository;
import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.IRepository;
import co.edu.unicauca.openmarket.domain.Category;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Elkin
 */
public class CategoryServiceTest {
    
    private IRepository repositoryCategory = Factory.getInstance().getRepository("category");
    private CategoryService categoryService = new CategoryService(repositoryCategory);
    
    public CategoryServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * PRUEBA PARA METODO SAVE() 
     * Verifica que se guarde correctamente la categoria
     */
    @Test
        public void testSaveCategory() {
        System.out.println("save");
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        assertTrue(guardar); 
    }

    /**
     * PRUEBA PARA METODO FINDALLCATEGORIES() 
     * Verifica que se devuelva una lista con las categoria existentes
     */
    @Test
    public void testFindAllCategories() {
        System.out.println("findAllCategories");
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");
        
        Category categoria1 = new Category();
        categoria1.setCategoryId(2L);
        categoria1.setName("Frutas");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        boolean guardar1 = categoryService.saveCategory(categoria1.getName());
        
        assertTrue(guardar); 
        assertTrue(guardar1);

        List<Category> listaCategorias = categoryService.findAllCategories();      
        assertNotNull(listaCategorias);
        assertEquals(2, listaCategorias.size());

        Object primeraCat = listaCategorias.get(0);
        Object segundaCat = listaCategorias.get(1);

        assertTrue(primeraCat instanceof Category);
        assertTrue(segundaCat instanceof Category);

        Category categoriaEncontrada1 = (Category) primeraCat;
        Category categoriaEncontrada2 = (Category) segundaCat;

        assertEquals("Carbohidratos", categoriaEncontrada1.getName());
        assertEquals("Frutas", categoriaEncontrada2.getName());
    }

    /**
     * PRUEBA PARA METODO FINDCATEGORYBYID() 
     * Verifica que se pueda encuentrar la categoria por id
     */
    @Test
    public void testFindCategoryById() {
        System.out.println("findCategoryById"); 
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        assertTrue(guardar); 

        Long categoryId = categoria.getCategoryId();
        assertNotNull(categoryId);

        Category encontrarCategoria = categoryService.findCategoryById(categoryId);

        assertNotNull(encontrarCategoria);
        assertEquals("Carbohidratos", encontrarCategoria.getName());
    }

     /**
     * PRUEBA PARA METODO DELETECATEGORY() 
     * Verifica que se elimine correctamente una categoria
     */
    @Test
    public void testDeleteCategory() {
        System.out.println("deleteCategory");
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        assertTrue(guardar); 

        Long categoryId = categoria.getCategoryId();
        assertNotNull(categoryId);

        boolean borrada = categoryService.deleteCategory(categoryId);
        assertTrue(borrada);

        Category categoriaEliminada = categoryService.findCategoryById(categoryId);
        assertNull(categoriaEliminada);
    }

    /**
     * PRUEBA PARA METODO FINDCATEGORYBYNAME() 
     * Verifica que se pueda encuentrar la categoria por su nombre
     */
    @Test
    public void testFindCategoryByName() {
        System.out.println("findCategoryByName");
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        assertTrue(guardar); 

        List<Category> listaCategorias = categoryService.findCategoryByName("Carbohidratos");

        assertNotNull(listaCategorias);
        assertEquals(1, listaCategorias.size());

        Object primeraCategoria = listaCategorias.get(0);
        assertTrue(primeraCategoria instanceof Category);

        Category foundCategory = (Category) primeraCategoria;
        assertEquals("Carbohidratos", foundCategory.getName());
    }

    /**
     * PRUEBA PARA METODO EDITCATEGORY() 
     * Verifica que se pueda editar correctamente la categoria
     */
    @Test
    public void testEditCategory() {
        System.out.println("editCategory");
        
        Category categoria = new Category();
        categoria.setCategoryId(1L);
        categoria.setName("Carbohidratos");

        boolean guardar = categoryService.saveCategory(categoria.getName());
        assertTrue(guardar); 

        categoria.setName("Frutas");
        
        boolean editar = categoryService.editCategory(categoria.getCategoryId(), categoria);
        assertTrue(editar); 
        
        List<Category> foundCategory = categoryService.findCategoryByName("Frutas");
        
        assertNotNull(foundCategory);
        assertEquals(1, foundCategory.size());
        
        System.out.println("Productos:\n"+foundCategory.get(0).getName());

    }
    
}
