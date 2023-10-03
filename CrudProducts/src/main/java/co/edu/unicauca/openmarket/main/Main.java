package co.edu.unicauca.openmarket.main;

import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.IRepository;
import co.edu.unicauca.openmarket.access.ISearch;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.edu.unicauca.openmarket.presentation.GUIMain;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IRepository repositoryProduct = Factory.getInstance().getRepository("product");
        ISearch search = Factory.getInstance().getSearch("product");
        ProductService productService = new ProductService(repositoryProduct, search);
        IRepository repositoryCategory = Factory.getInstance().getRepository("category");
        CategoryService categoryService = new CategoryService(repositoryCategory);

        GUIMain instance = new GUIMain(productService, categoryService);
        instance.setVisible(true);
    }

}
