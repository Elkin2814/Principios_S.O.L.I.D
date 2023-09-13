/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.AssistentDB;
import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.IRepository;
import co.edu.unicauca.openmarket.access.ISearch;
import co.edu.unicauca.openmarket.access.ProductRepository;
import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
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
public class ProductServiceTest {

    private IRepository repositoryProduct = Factory.getInstance().getRepository("product");
    private ISearch search = Factory.getInstance().getSearch("product");
    private ProductService productService = new ProductService(repositoryProduct, search);

    private IRepository repositoryCategory = Factory.getInstance().getRepository("category");
    private CategoryService categoryService = new CategoryService(repositoryCategory);

    public ProductServiceTest() {
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
     * Test of saveProduct method, of class ProductService.
     */
    @Test
    public void testSaveProduct() {
        System.out.println("saveProduct");

        Product product = new Product();
        product.setProductId(1L);
        product.setName("Arroz");
        product.setDescription("Blanco y rico");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Carbohidratos");
        product.setCategory(category);

        boolean saved = productService.saveProduct(product.getName(), product.getDescription(), category.getCategoryId());
        assertTrue(saved);

    }

    /**
     * Test of findAllProducts method, of class ProductService.
     */
    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");

        Category verduras = new Category();
        verduras.setCategoryId(1L);
        verduras.setName("Verduras");

        Product producto1 = new Product();
        producto1.setProductId(1L);
        producto1.setName("Espinaca");
        producto1.setDescription("Es verde");
        producto1.setCategory(verduras);

        Product producto2 = new Product();
        producto2.setProductId(2L);
        producto2.setName("Tomate");
        producto2.setDescription("Es rojo");
        producto2.setCategory(verduras);

        boolean saved3 = categoryService.saveCategory(verduras.getName());
        productService = new ProductService(repositoryProduct, search);
        assertTrue(saved3);
        boolean saved = productService.saveProduct(producto1.getName(), producto1.getDescription(), verduras.getCategoryId());
        boolean saved2 = productService.saveProduct(producto2.getName(), producto2.getDescription(), verduras.getCategoryId());
        assertTrue(saved);
        assertTrue(saved2);

        List<Product> result = productService.findAllProducts();
        assertEquals(2, result.size());
    }

    /**
     * Test of findProductById method, of class ProductService.
     */
    @Test
    public void testFindProductById() {
        System.out.println("findProductById");
        Category category = new Category();
        Long i = 1L;
        category.setCategoryId(i);
        category.setName("Verduras");
        boolean saved3 = categoryService.saveCategory(category.getName());
        assertTrue(saved3);
        Category c = categoryService.findCategoryById(i);
        assertNotNull(c);
        Product newProduct = new Product();
        newProduct.setName("Tomate");
        newProduct.setProductId(1L);
        newProduct.setDescription("Rojo");
        newProduct.setCategory(category);
        boolean saved = productService.saveProduct(newProduct.getName(), newProduct.getDescription(), category.getCategoryId());
        assertTrue(saved);
        Product p = productService.findProductById(1L);
        assertNotNull(p);
        assertEquals(newProduct.getProductId(), p.getProductId());
        System.out.println(""+ newProduct.getProductId()+":"+ p.getProductId());
    }

    /**
     * Test of deleteProduct method, of class ProductService.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        Long id = null;
        ProductService instance = null;
        boolean expResult = false;
        boolean result = instance.deleteProduct(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductByName method, of class ProductService.
     */
    @Test
    public void testFindProductByName() {
        System.out.println("findProductByName");
        String name = "";
        ProductService instance = null;
        List<Product> expResult = null;
        List<Product> result = instance.findProductByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProductByCategory method, of class ProductService.
     */
    @Test
    public void testFindProductByCategory() {
        System.out.println("findProductByCategory");
        String name = "";
        ProductService instance = null;
        List<Product> expResult = null;
        List<Product> result = instance.findProductByCategory(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editProduct method, of class ProductService.
     */
    @Test
    public void testEditProduct() {
        System.out.println("editProduct");
        Long productId = null;
        Product prod = null;
        ProductService instance = null;
        boolean expResult = false;
        boolean result = instance.editProduct(productId, prod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
