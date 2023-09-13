/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

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
public class FactoryTest {

    public FactoryTest() {
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
     * PRUEBA PARA METODO GETINSTANCE() Verifica que sla instancia de Factory no
     * sea nula
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Factory instance = Factory.getInstance();

        assertNotNull(instance);
    }

    /**
     * PRUEBA PARA METODO GETREPOSITORY() Verifica que getRepository devuelve
     * null cuando se pasa una cadena vacía como tipo
     */
    @Test
    public void testGetRepository() {
        System.out.println("getRepository");
        Factory factory = Factory.getInstance();

        IRepository repository = factory.getRepository("");
        assertNull(repository);

        //assertTrue(repository instanceof CategoryRepository);
    }

    /**
     * PRUEBA PARA METODO GETSEARCH() Verifica que getSearch devuelva null
     * cuando se pasa una cadena vacía como tipo
     */
    @Test
    public void testGetSearch() {
        System.out.println("getSearch");
        Factory factory = Factory.getInstance();

        ISearch search = factory.getSearch("");
        assertNull(search);
    }

}
