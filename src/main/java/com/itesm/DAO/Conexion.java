/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.DAO;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmanuelpaez
 */
public class Conexion {
    
    public JDBCConnectionPool conectar (){
        JDBCConnectionPool pool = null;
        try {
             pool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/web", "root", "", 2, 5);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return pool;
    }
    
}
