
package com.itesm.DAO;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Distritos {
    
    private SQLContainer container;
    public Distritos(){
      JDBCConnectionPool pool = new Conexion().conectar();
      TableQuery tq = new TableQuery("Distrito", pool);
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
