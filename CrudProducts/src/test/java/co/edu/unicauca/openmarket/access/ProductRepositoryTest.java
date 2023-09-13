/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class ProductRepositoryTest {

    private Connection conn;

    public ProductRepositoryTest() {
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
     * PRUEBA PARA METODO SAVE() Verifica que se guarde correctamente el
     * producto
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Product product = new Product();
        product.setName("Arroz");
        product.setDescription("Blanco y rico");
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Carbohidratos");
        product.setCategory(category);

        ProductRepository instance = new ProductRepository();
        boolean result = instance.save(product);
        assertTrue(result);
    }

    /**
     * Test of findAll method, of class ProductRepository.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");

        Category verduras = new Category();
        verduras.setCategoryId(3L);
        verduras.setName("Verduras");

        Product producto1 = new Product();
        producto1.setProductId(1L);
        producto1.setName("Espinaca");
        producto1.setDescription("Es verde");
        producto1.setCategory(verduras);

        Product producto2 = new Product();
        producto2.setProductId(3L);
        producto2.setName("Tomate");
        producto2.setDescription("Es rojo");
        producto2.setCategory(verduras);

        ProductRepository instance = new ProductRepository();
        boolean saved1 = instance.save(producto1);
        //boolean saved2 = instance.save(producto2);

        assertTrue(saved1);
        //assertTrue(saved2);

        List<Object> result = instance.findAll();

        System.out.println(producto1.getCategory().getCategoryId());

        assertNotNull(result);
        assertEquals(1, result.size());

    }

    /**
     * PRUEBA PARA METODO EDIT() Verifica que se edite correctamente el producto
     */
    @Test
    public void testEdit() {
        System.out.println("edit");

        Product nuevoProducto = new Product();
        nuevoProducto.setProductId(1L);
        nuevoProducto.setName("Totame");
        nuevoProducto.setDescription("Rojo");

        Category category = new Category();
        category.setCategoryId(2L);
        category.setName("Verduras");

        nuevoProducto.setCategory(category);

        ProductRepository instance = new ProductRepository();

        boolean saved = instance.save(nuevoProducto);
        assertTrue(saved);

        assertEquals(nuevoProducto.getCategory().getCategoryId(), category.getCategoryId());

        nuevoProducto.setName("Cebolla");
        nuevoProducto.setDescription("Blanca");

        boolean edited = instance.edit(nuevoProducto.getCategory().getCategoryId(), nuevoProducto);
        assertTrue(edited);

    }

    /**
     * PRUEBA PARA METODO DELETE() Verifica que se elimine correctamente el
     * producto
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Product producto = new Product();
        producto.setProductId(1L);
        producto.setName("Tomate");
        producto.setDescription("Rojo");
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Verduras");
        producto.setCategory(category);

        ProductRepository instance = new ProductRepository();

        boolean saved = instance.save(producto);
        assertTrue(saved);

        Long productId = producto.getProductId();

        boolean deleted = instance.delete(productId);
        assertTrue(deleted);

        Product deletedProduct = (Product) instance.findById(productId);

        assertNull(deletedProduct);
    }

    /**
     * Test of findById method, of class ProductRepository.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");

        Product newProduct = new Product();
        newProduct.setName("Tomate");
        newProduct.setProductId(1L);
        newProduct.setDescription("Rojo");

        Product newProduct2 = new Product();
        newProduct2.setName("Cebolla");
        newProduct2.setProductId(2L);
        newProduct2.setDescription("Blanca");

        Category category = new Category();
        category.setCategoryId(2L);
        category.setName("Verduras");
        newProduct.setCategory(category);
        newProduct2.setCategory(category);

        ProductRepository instance = new ProductRepository();

        boolean saved = instance.save(newProduct);
        assertTrue(saved);

        assertEquals(newProduct.getCategory().getCategoryId(), category.getCategoryId());

        Long productId = newProduct.getProductId();

        Product encontrarProducto = (Product) instance.findById(productId);
        System.out.println(encontrarProducto.getName());
    }

    /**
     * Test of findByName method, of class ProductRepository.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");

        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setName("Frutas");

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Manzana");
        product1.setDescription("Fruta roja");
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Espinaca");
        product2.setDescription("Verdura verde");
        product2.setCategory(category1);

        Product product3 = new Product();
        product3.setProductId(3L);
        product3.setName("Plátano");
        product3.setDescription("Fruta amarilla");
        product3.setCategory(category1);

        ProductRepository instance = new ProductRepository();

        // Guardar los productos en la base de datos
        boolean saved1 = instance.save(product1);
        boolean saved2 = instance.save(product2);
        boolean saved3 = instance.save(product3);

        assertTrue(saved1);
        assertTrue(saved2);
        assertTrue(saved3);

        List<Object> foundProducts = instance.findByName("Manzana");

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());

        Object firstProduct = foundProducts.get(0);
        //Object secondProduct = foundProducts.get(1);

        assertTrue(firstProduct instanceof Product);
        //assertTrue(secondProduct instanceof Product);

        Product foundProduct1 = (Product) firstProduct;
        //Product foundProduct2 = (Product) secondProduct;

        assertEquals("Manzana", foundProduct1.getName());
        //assertEquals("Manzana", foundProduct2.getName());
    }

    /**
     * Test of findProductByCategory method, of class ProductRepository.
     */
    @Test
    public void testFindProductByCategory() {
        System.out.println("findProductByCategory");
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Frutas");

         Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Manzana");
        product1.setDescription("Fruta roja");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Espinaca");
        product2.setDescription("Verdura verde");
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setProductId(3L);
        product3.setName("Plátano");
        product3.setDescription("Fruta amarilla");
        product3.setCategory(category);

        ProductRepository instance = new ProductRepository();

        boolean saved1 = instance.save(product1);
        boolean saved2 = instance.save(product2);
        boolean saved3 = instance.save(product3);

        assertTrue(saved1);
        assertTrue(saved2);
        assertTrue(saved3);

        List<Object> foundProducts = instance.findProductByCategory("Frutas");

        assertNotNull(foundProducts);
        assertEquals(3, foundProducts.size());

        for (Object obj : foundProducts) {
            assertTrue(obj instanceof Product);
            Product foundProduct = (Product) obj;
            assertEquals("Frutas", foundProduct.getCategory().getName());
        }
    }

}
