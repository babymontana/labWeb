/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.DAO;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmanuelpaez
 */
public class Categorias {
    
    private SQLContainer container;
    public Categorias(){
      JDBCConnectionPool pool = new Conexion().conectar();
      TableQuery tq = new TableQuery("Categoria", pool);
      tq.setVersionColumn("OPTLOCK");
        try {
            container = new SQLContainer(tq);
        } catch (SQLException ex) {
            Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SQLContainer getContainer() {
        return container;
    }

    public void setContainer(SQLContainer container) {
        this.container = container;
    }
    
    
    
    
}
