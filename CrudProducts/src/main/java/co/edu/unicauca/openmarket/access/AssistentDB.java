/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elkin
 */
public class AssistentDB implements IAssistentDB {

    private Connection conn;

    public AssistentDB() {
        connect();
        initDatabaseProduct();
        initDataBaseCategory();
    }

    @Override
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows

        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

        } catch (SQLException ex) {
            Logger.getLogger(AssistentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void initDatabaseProduct() {
        // SQL statement for creating a new table
        String sql ="CREATE TABLE IF NOT EXISTS products (" +
        "productId INT AUTO_INCREMENT PRIMARY KEY," +
        "name VARCHAR(20)," +
        "categoryId INT," +
        "description VARCHAR(100)" +
        ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(AssistentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initDataBaseCategory() {
        // SQL statement for creating a new table
        String sql ="CREATE TABLE IF NOT EXISTS category (" +
        "categoryId INT AUTO_INCREMENT PRIMARY KEY," +
        "nameCategory VARCHAR(25)" +
        ")";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(AssistentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
