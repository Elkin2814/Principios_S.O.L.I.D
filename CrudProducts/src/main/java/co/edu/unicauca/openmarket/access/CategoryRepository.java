/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ideapad330S
 */
public class CategoryRepository implements ICategoryRepository{

    private AssistentDB conn = new AssistentDB();
    
    public CategoryRepository(){
        conn.initDataBaseCategory();
    }
    @Override
    public boolean save(Category newCategory) {
    try {
            //Validate product
            if (newCategory == null || newCategory.getName().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO category ( name ) "
                    + "VALUES ( ? )";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, newCategory.getName());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean edit(Long id, Category Cate) {
         try {
            //Validate product
            if (id <= 0 || Cate == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  category "
                    + "SET name=? "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, Cate.getName());
            pstmt.setLong(2, id);
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

            String sql = "DELETE FROM category "
                    + "WHERE categoryId = ?";

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
    public Category findById(Long id) {
            try {

            String sql = "SELECT * FROM category  "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category categ = new Category();
                categ.setCategoryId(res.getLong("categoryId"));
                categ.setName(res.getString("name"));
                return categ;
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
    public List<Category> findByName(String name) {
        try {
             List<Category> categories = new ArrayList<>();
            String sql = "SELECT * FROM category  "
                    + "WHERE name = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category categ = new Category();
                categ.setCategoryId(res.getLong("categoryId"));
                categ.setName(res.getString("name"));
                categories.add(categ);
                return categories;
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
    public List<Category> findAll() {
         List<Category> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM category";
            //this.connect();

            Statement stmt = conn.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category categ = new Category();
                categ.setCategoryId(rs.getLong("categorytId"));
                categ.setName(rs.getString("name"));
                categories.add(categ);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
}
