
package com.itesm.classes;


import com.vaadin.data.util.sqlcontainer.SQLContainer;


public class Distritos {
    
    SQLContainer distritosData;
   
   public Distritos(){
       
       com.itesm.DAO.Distritos data = new com.itesm.DAO.Distritos();
       distritosData = data.getContainer();
      
}

    public SQLContainer getDistritosData() {
        return distritosData;
    }

    public void setDistritosData(SQLContainer distritosData) {
        this.distritosData = distritosData;
    }

    

    
    
}
