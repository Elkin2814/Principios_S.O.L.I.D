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
public class CategoryRepository implements IRepository{

    private AssistentDB conn = new AssistentDB();
    
    public CategoryRepository(){
        conn.initDataBaseCategory();
    }
    @Override
    public boolean save(Object o) {
    try {
            Category newCategory = (Category) o;
            //Validate category
            if (newCategory == null || newCategory.getName().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO category ( nameCategory ) "
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
    public boolean edit(Long id, Object o) {
        Category Cate = (Category) o;
         try {
            //Validate product
            if (id <= 0 || o == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  category "
                    + "SET nameCategory =? "
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
                categ.setName(res.getString("nameCategory"));
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
    public List<Object> findByName(String name) {
        try {
             List<Object> categories = new ArrayList<>();
            String sql = "SELECT * FROM category  "
                    + "WHERE nameCategory  = ?";

            PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category categ = new Category();
                categ.setCategoryId(res.getLong("categoryId"));
                categ.setName(res.getString("nameCategory"));
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
    public List<Object> findAll() {
         List<Object> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM category";
            //this.connect();

            Statement stmt = conn.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category categ = new Category();
                categ.setCategoryId(rs.getLong("categoryId"));
                categ.setName(rs.getString("nameCategory"));
                categories.add(categ);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
}
