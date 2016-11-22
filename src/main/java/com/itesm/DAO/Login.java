/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.DAO;

import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmanuelpaez
 */
public class Login {
    public int getData(String user, String password){
         int size=0;
        try {
            JDBCConnectionPool pool = new Conexion().conectar();
            TableQuery tq = new TableQuery("Administrador", pool);
            tq.setVersionColumn("OPTLOCK");
           SQLContainer container = new SQLContainer(tq);
            container.addContainerFilter(new And(new Compare.Equal("ADMINISTRATOR_NAME",user),new Compare.Equal("ADMINISTRATOR_PASSWORD",password)));
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("id", container.firstItemId());

            size = container.size();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return size;
    }
}
