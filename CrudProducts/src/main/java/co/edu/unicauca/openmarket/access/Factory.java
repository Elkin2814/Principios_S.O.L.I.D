package co.edu.unicauca.openmarket.access;

/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    private static Factory instance;
    


    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public IRepository getRepository(String type) {
        IRepository result = null;
        switch (type) {
            case "category":
                result = new CategoryRepository();
                break;

            case "product":
                result = new ProductRepository();
                break;
        }
        return result;
    }
    
        public ISearch getSearch(String type) {
        ISearch result = null;
        switch (type) {
            case "product":
                result = new ProductRepository();
                break;
        }
        return result;
    }
    
}
