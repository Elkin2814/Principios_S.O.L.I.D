/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.openmarket.access;


import java.util.List;


/**
 *
 * @author ideapad330S
 */
public interface IRepository {
    boolean save(Object o );
    
    boolean edit(Long id , Object o );
    
    boolean delete(Long id);

    Object findById(Long id);
    
    List<Object> findByName(String name);
    
    List<Object> findAll();
}
