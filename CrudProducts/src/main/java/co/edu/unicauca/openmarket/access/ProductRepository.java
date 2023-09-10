package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Es una implementación que tiene libertad de hacer una implementación del
 * contrato. Lo puede hacer con Sqlite, postgres, mysql, u otra tecnología
 *
 * @author Libardo, Julio
 */
public class ProductRepository implements IRepository, ISearch {

    private AssistentDB conn = new AssistentDB();

    public ProductRepository() {
        conn.initDatabaseProduct();
    }

    @Override
    public boolean save(Object o) {
        Product newProduct = (Product) o;
        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO products ( name, categoryId, description ) "
                    + "VALUES ( ?, ? ,?)";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getCategory().getCategoryId().toString());
            pstmt.setString(3, newProduct.getDescription());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Object> findAll() {
        List<Object> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products JOIN category  ON (products.categoryId = category.categoryId)";
            //this.connect();

            Statement stmt = conn.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                Category ca = new Category();
                ca.setCategoryId(rs.getLong("categoryId"));
                ca.setName(rs.getString("nameCategory"));
                newProduct.setCategory(ca);
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public boolean edit(Long id, Object o) {
        Product product = (Product) o;
        try {
            //Validate product
            if (id <= 0 || product == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  products "
                    + "SET name=?, description=? , categoryId"
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setLong(3, id);
            pstmt.setLong(4, product.getCategory().getCategoryId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Object findById(Long id) {
        try {

            String sql = "SELECT * FROM products JOIN category  ON (products.categoryId = category.categoryId)  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                Category ca = new Category();
                ca.setCategoryId(res.getLong("categoryId"));
                ca.setName(res.getString("nameCategory"));
                prod.setCategory(ca);
                return prod;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Object> findByName(String name) {
        try {
            List<Object> products = new ArrayList<>();
            String sql = "SELECT * FROM products JOIN category  ON (products.categoryId = category.categoryId) "
                    + "WHERE name = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                Category ca = new Category();
                ca.setCategoryId(res.getLong("categoryId"));
                ca.setName(res.getString("nameCategory"));
                prod.setCategory(ca);
                products.add(prod);
                return products;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Object> findProductByCategory(String name) {
        List<Object> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products JOIN category  ON (products.categoryId = category.categoryId)"
                    + "WHERE category.nameCategory = ?";
            //this.connect();

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                Category ca = new Category();
                ca.setCategoryId(rs.getLong("categoryId"));
                ca.setName(rs.getString("nameCategory"));
                newProduct.setCategory(ca);
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

}
