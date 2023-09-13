/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class AssistentDBTest {
    
    public AssistentDBTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    /**
     * PRUEBA PARA METODO CONNECT()     
     * Verifica que la conexion este abierta por al menos 5 segundos
     */
    @org.junit.jupiter.api.Test
    public void testConnect() throws SQLException {
        System.out.println("connect");
        AssistentDB instance = new AssistentDB();
        instance.connect();
        assertNotNull(instance.getConn());
        assertTrue(instance.getConn().isValid(5)); 
    }

    /**
     * PRUEBA PARA METODO DISCONNECT()     
     * Verifica que la conexion se haga NULA despues de 
     * desconectarla
     */
    @org.junit.jupiter.api.Test
    public void testDisconnect() throws SQLException {
        System.out.println("disconnect");
        AssistentDB instance = new AssistentDB();
        instance.connect();
        instance.disconnect();
        assertTrue(instance.getConn() == null);
    }

    /**
     * PRUEBA PARA METODO GETCONN()     
     * Verifica que la conexion se haga NULA despues de 
     * desconectarla
     */
    @org.junit.jupiter.api.Test
    public void testGetConn() {
        System.out.println("getConn");
        AssistentDB instance = new AssistentDB();
        instance.disconnect();
        assertNull(instance.getConn());
    }

    /**
     * PRUEBA PARA METODO SETCONN()     
     * Verifica que la conexion regrese la conexion nula establecida
     */
    @org.junit.jupiter.api.Test
    public void testSetConn() {
        System.out.println("setConn");
        Connection conn = null;
        AssistentDB instance = new AssistentDB();
        instance.setConn(conn);
        assertEquals(conn, instance.getConn());
    }

    /**
     * PRUEBA PARA METODO INITDATABASEPRODUCT()     
     * Verifica que la tabla PRODUCT exista al momento de ingresar al metodo
     */
    @org.junit.jupiter.api.Test
    public void testInitDatabaseProduct() {
        System.out.println("initDatabaseProduct");
        AssistentDB instance = new AssistentDB();
        instance.initDatabaseProduct();
        String tabla = "products";
        
        boolean tableExists = isTableExists(instance.getConn(), tabla);
        System.out.println("LA TABLA " + tabla + " EXISTE");
        assertTrue(tableExists); 
    }

    //METODO PARA COMPROBAR LA EXISTENCIA DE TABLAS
    private boolean isTableExists(Connection conn, String tabla) {
        try {
            String sql = "SELECT 1 FROM " + tabla + " LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * PRUEBA PARA METODO INITDATABASECATEGORY()     
     * Verifica que la tabla CATEGORY exista al momento de ingresar al metodo
     */
    @org.junit.jupiter.api.Test
    public void testInitDataBaseCategory() {
        System.out.println("initDataBaseCategory");
        AssistentDB instance = new AssistentDB();
        instance.initDataBaseCategory();
 
        boolean isTableCreated = isTableExists(instance.getConn(), "category");
        assertTrue(isTableCreated);
    }

    
}
