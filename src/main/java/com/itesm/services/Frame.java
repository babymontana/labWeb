/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.services;

import com.itesm.classes.Login;
import com.itesm.classes.checkLogged;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.File;

/**
 *
 * @author emmanuelpaez
 */

public class Frame   {
    
    
    private VerticalLayout content = new VerticalLayout();
    
     public VerticalLayout iniciar(UI ui){
         if(new checkLogged().validate(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("valido"))){
             return logged(ui);
         }else{
             return notLogged(ui);
         }
     }

    public VerticalLayout logged(UI ui){
        //creamos layout general
        final VerticalLayout layout = new VerticalLayout();
        
        //creamos titulo
        final HorizontalLayout title = new HorizontalLayout();
        Label titulo = new Label("Crime report");
        titulo.addStyleName(Reindeer.LABEL_H1);
        String basepath = VaadinService.getCurrent()
                  .getBaseDirectory().getAbsolutePath();       
        FileResource resource = new FileResource(new File(basepath +
                                "/WEB-INF/images/police.png"));

        Image image = new Image("", resource);
        image.setHeight("30px");
        title.setSpacing(true);
        title.addComponent(titulo);
        title.addComponent(image);
       
        layout.addComponent(title);
        //agregamos linea separadora
         layout.addComponent(new Label("<hr />",Label.CONTENT_XHTML));
         
         //creamos layout de menu y contenido
          final HorizontalLayout panel = new HorizontalLayout();
          //cramos menu
          final VerticalLayout menu = new VerticalLayout();
          Tree tree = new Tree("Menú");
          tree.addItem("Crimenes");
          tree.addItem("Nuevo Crimen");
          tree.setChildrenAllowed("Nuevo Crimen", false);
          tree.addItem("Buscar Crimen");
          tree.setChildrenAllowed("Buscar Crimen", false);
          
          tree.setParent("Nuevo Crimen","Crimenes");
          tree.setParent("Buscar Crimen","Crimenes");
          
          tree.addItem("Reporte");
          
          tree.addItem("Estadisticas");
          tree.setChildrenAllowed("Estadisticas", false);
          tree.addItem("Gráficas");
          tree.setChildrenAllowed("Gráficas", false);
          
          tree.setParent("Estadisticas","Reporte");
          tree.setParent("Gráficas","Reporte");
          
          tree.addItem("Sesión");
          
          tree.addItem("Finalizar Sesión");
          tree.setParent("Finalizar Sesión","Sesión");
          tree.setChildrenAllowed("Finalizar Sesión", false);
          
          
          tree.addItem("Acerca de");
          tree.setChildrenAllowed("Acerca de", false);
          
          tree.addItemClickListener(
            new ItemClickEvent.ItemClickListener() {
              public void itemClick(ItemClickEvent event) {
                  // Pick only left mouse clicks
                    if (event.getItemId().toString().equals("Nuevo Crimen")){
                        ui.getPage().setLocation("addCrime");
                        
                    }
                    
                     if (event.getItemId().toString().equals("Buscar Crimen")){
                        ui.getPage().setLocation("searchCrime");
                    }
                     
                      if (event.getItemId().toString().equals("Estadisticas")){
                        ui.getPage().setLocation("estadisticas");
                    }
                      
                       if (event.getItemId().toString().equals("Gráficas")){
                        ui.getPage().setLocation("graficas");
                    }
                       
                        if (event.getItemId().toString().equals("Finalizar Sesión")){
                        ui.getPage().setLocation("endSession");
                    }
                        
                         if (event.getItemId().toString().equals("Acerca de")){
                        ui.getPage().setLocation("about");
                    }
                  }
        });
          

          
       
          menu.addComponent(tree);
          menu.setWidth("40%");
          panel.addComponent(menu);
          this.content.setWidth("60%");
          this.content.setMargin(new MarginInfo(false, true, false, true));
          panel.addComponent(this.content);
          layout.addComponent(panel);
          layout.setMargin(new MarginInfo(false, true, false, true));
          
          
         return layout;
    }
    
    public VerticalLayout notLogged(UI ui){
         final VerticalLayout layout = new VerticalLayout();
         Label titulo = new Label("Sesión no iniciada");
          titulo.addStyleName(Reindeer.LABEL_H1);
          layout.addComponent(titulo);
          layout.addComponent(new Label("Debes iniciar sesión primero."));
                Button boton = new Button("Ir");
        boton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                 ui.getPage().setLocation("/FinalProyecto");
            }
        });   
        layout.addComponent(boton);
         layout.setMargin(new MarginInfo(false, true, false, true));
         return layout;
    }

    public VerticalLayout getContent() {
        return content;
    }

    public void setContent(VerticalLayout content) {
        this.content = content;
    }

    
    
}
