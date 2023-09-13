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

        IRepository repositoryProduct = Factory.getInstance().getRepository("product");
        ISearch search = Factory.getInstance().getSearch("product");
        ProductService productService = new ProductService(repositoryProduct, search);

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

        IRepository repositoryProduct = Factory.getInstance().getRepository("product");
        ISearch search = Factory.getInstance().getSearch("product");
        productService = new ProductService(repositoryProduct, search);

        boolean saved = productService.saveProduct(producto1.getName(), producto1.getDescription(), verduras.getCategoryId());
        boolean saved2 = productService.saveProduct(producto2.getName(), producto2.getDescription(), verduras.getCategoryId());
        assertTrue(saved);
        assertTrue(saved2);
        
        List<Product> result = productService.findAllProducts();

        assertNotNull(result);
        System.out.println(result.get(0).getName());
        //assertEquals(2, result.size());
    }

    /**
     * Test of findProductById method, of class ProductService.
     */
    @Test
    public void testFindProductById() {
        
        System.out.println("findProductById");
        AssistentDB instance = new AssistentDB();
        instance.initDatabaseProduct();
        
        Product newProduct = new Product();
        newProduct.setName("Tomate");
        //newProduct.setProductId(1L);
        newProduct.setDescription("Rojo");

        Product newProduct2 = new Product();
        newProduct2.setName("Cebolla");
        newProduct2.setProductId(2L);
        newProduct2.setDescription("Blanca");

        Category category = new Category();
        category.setCategoryId(2L);
        category.setName("Verduras");
        newProduct.setCategory(category);
        //newProduct2.setCategory(category);


        boolean saved = productService.saveProduct(newProduct.getName(), newProduct.getDescription(), category.getCategoryId());
        assertTrue(saved);

        //assertEquals(newProduct.getCategory().getCategoryId(), category.getCategoryId());

        Long productId = newProduct.getProductId();

        Product encontrarProducto = productService.findProductById(productId);
        System.out.println(encontrarProducto.getName());
        assertNotNull(encontrarProducto);
        
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
