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
     * PRUEBA PARA METODO FINDALLPRODUCTS() 
     * Verifica que encuentre correctamente el producto por id
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
     * PRUEBA PARA METODO FINDBYPRODUCTID() 
     * Verifica que encuentre correctamente el producto por id
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
     * PRUEBA PARA METODO DELETEPRODUCT() 
     * Verifica que se elimine correctamente el producto
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Verduras");

        boolean saved1 = categoryService.saveCategory(category.getName());
        assertTrue(saved1);
        Category c = categoryService.findCategoryById(category.getCategoryId());
        assertNotNull(c);
        
        Product producto = new Product();
        producto.setProductId(1L);
        producto.setName("Tomate");
        producto.setDescription("Rojo");
        producto.setCategory(category);
        
        boolean saved2 = productService.saveProduct(producto.getName(), producto.getDescription(), category.getCategoryId());
        assertTrue(saved2);
        Product p = productService.findProductById(producto.getProductId());
        assertNotNull(p);
        assertEquals(producto.getProductId(), p.getProductId());

        Long productId = producto.getProductId();

        boolean deleted = productService.deleteProduct(productId);
        assertTrue(deleted);
        Product deletedProduct = productService.findProductById(productId);
        assertNull(deletedProduct);
    }

   /**
     * PRUEBA PARA METODO FINDPRODUCTNAME() 
     * Verifica que se encuentre correctamente el producto por su nombre
     */
    @Test
    public void testFindProductByName() {
        System.out.println("findProductByName");
        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setName("Frutas");
        
        boolean saved = categoryService.saveCategory(category1.getName());
        assertTrue(saved);
        Category c = categoryService.findCategoryById(category1.getCategoryId());
        assertNotNull(c);

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Manzana");
        product1.setDescription("Fruta roja");
        product1.setCategory(category1);
        
        boolean saved1 = productService.saveProduct(product1.getName(), product1.getDescription(), category1.getCategoryId());
        assertTrue(saved1);
        Product p1 = productService.findProductById(product1.getProductId());
        assertNotNull(p1);
        assertEquals(product1.getProductId(), p1.getProductId());

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Manzana Verde");
        product2.setDescription("Fruta verde");
        product2.setCategory(category1);
        
        boolean saved2 = productService.saveProduct(product2.getName(), product2.getDescription(), category1.getCategoryId());
        assertTrue(saved2);
        Product p2 = productService.findProductById(product2.getProductId());
        assertNotNull(p2);
        assertEquals(product2.getProductId(), p2.getProductId());

        Product product3 = new Product();
        product3.setProductId(3L);
        product3.setName("Plátano");
        product3.setDescription("Fruta amarilla");
        product3.setCategory(category1);
        
        boolean saved3 = productService.saveProduct(product3.getName(), product3.getDescription(), category1.getCategoryId());
        assertTrue(saved3);
        Product p3 = productService.findProductById(product3.getProductId());
        assertNotNull(p3);
        assertEquals(product3.getProductId(), p3.getProductId());

        List<Product> foundProducts = productService.findProductByName("Manzana");

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        
        System.out.println("Productos:\n"+foundProducts.get(0).getName());

        Object firstProduct = foundProducts.get(0);
        assertTrue(firstProduct instanceof Product);
        Product foundProduct1 = (Product) firstProduct;
        assertEquals("Manzana", foundProduct1.getName());
    }

   /**
     * PRUEBA PARA METODO FINDPRODUCTBYCATEGORY() 
     * Verifica que se encuentre correctamente los productos por su categoria
     */
    @Test
    public void testFindProductByCategory() {
        System.out.println("findProductByCategory");
        
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Frutas");
        
        boolean saved = categoryService.saveCategory(category.getName());
        assertTrue(saved);
        Category c = categoryService.findCategoryById(category.getCategoryId());
        assertNotNull(c);

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Manzana");
        product1.setDescription("Fruta roja");
        product1.setCategory(category);
        
        boolean saved1 = productService.saveProduct(product1.getName(), product1.getDescription(), category.getCategoryId());
        assertTrue(saved1);
        Product p1 = productService.findProductById(product1.getProductId());
        assertNotNull(p1);
        assertEquals(product1.getProductId(), p1.getProductId());

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Limon");
        product2.setDescription("Verdura verde");
        product2.setCategory(category);
        
        boolean saved2 = productService.saveProduct(product2.getName(), product2.getDescription(), category.getCategoryId());
        assertTrue(saved2);
        Product p2 = productService.findProductById(product2.getProductId());
        assertNotNull(p2);
        assertEquals(product2.getProductId(), p2.getProductId());

        Product product3 = new Product();
        product3.setProductId(3L);
        product3.setName("Plátano");
        product3.setDescription("Fruta amarilla");
        product3.setCategory(category);

        boolean saved3 = productService.saveProduct(product3.getName(), product3.getDescription(), category.getCategoryId());
        assertTrue(saved3);
        Product p3 = productService.findProductById(product3.getProductId());
        assertNotNull(p3);
        assertEquals(product3.getProductId(), p3.getProductId()); 

        List<Product> foundProducts = productService.findProductByCategory("Frutas");

        assertNotNull(foundProducts);
        assertEquals(3, foundProducts.size());

        for (Object obj : foundProducts) {
            assertTrue(obj instanceof Product);
            Product foundProduct = (Product) obj;
            assertEquals("Frutas", foundProduct.getCategory().getName());
        }
        
        /*for (int i = 0; i < foundProducts.size(); i++) {
            System.out.println("Fruta:"+foundProducts.get(i).getName());
        }*/
    }

 /**
     * PRUEBA PARA METODO EDIT() 
     * Verifica que se edite correctamente el producto
     */
    @Test
    public void testEditProduct() {
        System.out.println("editProduct"); 
        
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Frutas");
        
        boolean saved = categoryService.saveCategory(category.getName());
        assertTrue(saved);
        Category c = categoryService.findCategoryById(category.getCategoryId());
        assertNotNull(c);

        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Manzana");
        product1.setDescription("Fruta roja");
        product1.setCategory(category);
        
        boolean saved1 = productService.saveProduct(product1.getName(), product1.getDescription(), category.getCategoryId());
        assertTrue(saved1);
        Product p1 = productService.findProductById(product1.getProductId());
        assertNotNull(p1);
        assertEquals(product1.getProductId(), p1.getProductId());

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Limon");
        product2.setDescription("Verdura verde");
        product2.setCategory(category);
        
        boolean saved2 = productService.saveProduct(product2.getName(), product2.getDescription(), category.getCategoryId());
        assertTrue(saved2);
        Product p2 = productService.findProductById(product2.getProductId());
        assertNotNull(p2);
        assertEquals(product2.getProductId(), p2.getProductId());

        product1.setName("Cebolla");
        product1.setDescription("Blanca");

        boolean edited = productService.editProduct(product1.getProductId(), product1);
        assertTrue(edited);
        
        List<Product> foundProducts = productService.findProductByName("Cebolla");

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        
        System.out.println("Productos:\n"+foundProducts.get(0).getName());
    }

}
