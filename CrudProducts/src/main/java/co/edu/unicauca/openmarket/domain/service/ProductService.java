package co.edu.unicauca.openmarket.domain.service;


import co.edu.unicauca.openmarket.access.IRepository;
import co.edu.unicauca.openmarket.access.ISearch;
import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService {

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IRepository repository;
    private ISearch search;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductRepository
     */
    public ProductService(IRepository repository, ISearch search) {
        this.repository = repository;
        this.search = search;
    }

    public boolean saveProduct(String name, String description, Long idCategory) {

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        Category c = new Category();
        c.setCategoryId(idCategory);
        c.setName("Carnes");
        newProduct.setCategory(c);
        //Validate product
        if (newProduct.getName().isBlank()) {
            return false;
        }

        return repository.save(newProduct);

    }

    public List<Product> findAllProducts() {
        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos = repository.findAll();
        List<Product> listaProducts = listaObjetos.stream()
                .filter(objeto -> objeto instanceof Product)
                .map(objeto -> (Product) objeto)
                .collect(Collectors.toList());

        return listaProducts;
    }

    public Product findProductById(Long id) {
        return (Product) repository.findById(id);
    }

    public boolean deleteProduct(Long id) {
        return repository.delete(id);
    }

    public List<Product> findProductByName(String name) {
        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos = repository.findByName(name);
        List<Product> listaProducts = listaObjetos.stream()
                .filter(objeto -> objeto instanceof Product)
                .map(objeto -> (Product) objeto)
                .collect(Collectors.toList());
        return listaProducts;
    }
    
        public List<Product> findProductByCategory(String name) {
        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos = search.findProductByCategory(name);
        List<Product> listaProducts = listaObjetos.stream()
                .filter(objeto -> objeto instanceof Product)
                .map(objeto -> (Product) objeto)
                .collect(Collectors.toList());
        return listaProducts;
    }

    public boolean editProduct(Long productId, Product prod) {

        //Validate product
        if (prod == null || prod.getName().isBlank()) {
            return false;
        }
        return repository.edit(productId, prod);
    }

}
