/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

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
public class CategoryRepositoryTest {

    public CategoryRepositoryTest() {
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
     * PRUEBA PARA METODO SAVE() Verifica que se guarde correctamente la
     * categoria
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Category categoriaGuardada = new Category();
        categoriaGuardada.setName("Nueva Categoria");

        CategoryRepository instance = new CategoryRepository();
        boolean guardar = instance.save(categoriaGuardada);
        assertTrue(guardar);
        System.out.println(categoriaGuardada.getCategoryId());
    }

    /**
     * PRUEBA PARA METODO EDIT() 
     * Verifica que se pueda editar correctamente la categoria
     */
    @Test
    public void testEdit() {
        System.out.println("edit");

        Category categoriaNueva = new Category();
        categoriaNueva.setName("Nueva Categoria");

        CategoryRepository instance = new CategoryRepository();
        boolean guardada = instance.save(categoriaNueva);

        assertTrue(guardada);

        System.out.println(categoriaNueva.getName());

        int idCat = 1;
        long categoryId = idCat;

        //Long categoryId = categoriaNueva.getCategoryId();
        assertNotNull(categoryId);
        Category categoriaEditada = new Category();
        categoriaEditada.setName("Categoria Modificada");

        boolean editada = instance.edit(categoryId, categoriaEditada);

        assertTrue(editada);

        Category verificarCategoria = instance.findById(categoryId);
        assertNotNull(verificarCategoria);
        assertEquals("Categoria Modificada", verificarCategoria.getName());

        System.out.println(verificarCategoria.getName());
    }

    /**
     * PRUEBA PARA METODO DELETE() 
     * Verifica que se elimine correctamente una categoria
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Category categoriaNueva = new Category();
        categoriaNueva.setName("Categoria Eliminada");

        CategoryRepository instance = new CategoryRepository();
        boolean saved = instance.save(categoriaNueva);

        assertTrue(saved);

        int idCat = 1;
        long categoryId = idCat;

        //Long categoryId = categoriaNueva.getCategoryId();
        assertNotNull(categoryId);

        boolean borrada = instance.delete(categoryId);

        assertTrue(borrada);

        Category categoriaEliminada = instance.findById(categoryId);
        assertNull(categoriaEliminada);
    }

    /**
     * PRUEBA PARA METODO FINDBY() 
     * Verifica que se pueda encuentrar la categoria por id
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Category categoriaNueva = new Category();
        categoriaNueva.setName("Categoria Encontrada");

        CategoryRepository instance = new CategoryRepository();
        boolean guardada = instance.save(categoriaNueva);

        assertTrue(guardada);

        int idCat = 1;
        long categoryId = idCat;

        //Long categoryId = categoriaNueva.getCategoryId();
        assertNotNull(categoryId);

        Category encontrarCategoria = instance.findById(categoryId);

        assertNotNull(encontrarCategoria);
        assertEquals("Categoria Encontrada", encontrarCategoria.getName());
    }

    /**
     * PRUEBA PARA METODO FINDBYNAME() 
     * Verifica que se pueda encuentrar la categoria por su nombre
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        Category categoriaNueva = new Category();
        categoriaNueva.setName("Categoria Encontrada");

        CategoryRepository instance = new CategoryRepository();
        boolean guardada = instance.save(categoriaNueva);

        assertTrue(guardada);

        List<Object> listaCategorias = instance.findByName("Categoria Encontrada");

        assertNotNull(listaCategorias);
        assertEquals(1, listaCategorias.size());

        Object primeraCategoria = listaCategorias.get(0);

        assertTrue(primeraCategoria instanceof Category);

        Category foundCategory = (Category) primeraCategoria;
        assertEquals("Categoria Encontrada", foundCategory.getName());
    }

    /**
     * PRUEBA PARA METODO FINDALL() 
     * Verifica que se devuelva una lista con las categoria existentes
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        Category categoria1 = new Category();
        categoria1.setName("Categoria 1");
        Category categoria2 = new Category();
        categoria2.setName("Categoria 2");

        CategoryRepository instance = new CategoryRepository();
        boolean guardado1 = instance.save(categoria1);
        boolean guardado2 = instance.save(categoria2);

        assertTrue(guardado1);
        assertTrue(guardado2);

        List<Object> listaCategorias = instance.findAll();
        
        assertNotNull(listaCategorias);
        assertEquals(2, listaCategorias.size());

        Object primeraCat = listaCategorias.get(0);
        Object segundaCat = listaCategorias.get(1);

        assertTrue(primeraCat instanceof Category);
        assertTrue(segundaCat instanceof Category);

        Category categoriaEncontrada1 = (Category) primeraCat;
        Category categoriaEncontrada2 = (Category) segundaCat;

        assertEquals("Categoria 1", categoriaEncontrada1.getName());
        assertEquals("Categoria 2", categoriaEncontrada2.getName());
    }

}
