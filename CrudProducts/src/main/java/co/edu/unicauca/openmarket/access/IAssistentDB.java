/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

/**
 *
 * @author Elkin
 */
public interface IAssistentDB {

    void connect();

    void disconnect();

    void initDatabaseProduct();
    
    void initDataBaseCategory();
    
}
