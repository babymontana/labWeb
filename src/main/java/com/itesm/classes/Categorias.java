
package com.itesm.classes;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import java.util.ArrayList;


public class Categorias {
    
   SQLContainer categoriasData;
   
   public Categorias(){
       
       com.itesm.DAO.Categorias data = new com.itesm.DAO.Categorias();
       categoriasData = data.getContainer();
      
}

    public SQLContainer getCategoriasData() {
        return categoriasData;
    }

    public void setCategoriasData(SQLContainer categoriasData) {
        this.categoriasData = categoriasData;
    }
   
   
    
    
    
    
    
    
}
