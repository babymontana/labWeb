/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.services;

import com.itesm.classes.Categorias;
import com.itesm.classes.Delito;
import com.itesm.classes.Delitos;
import com.itesm.classes.Distritos;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.data.util.filter.Compare.Less;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author emmanuelpaez
 */
@Theme("mytheme")
public class SearchCrime extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Frame frame = new Frame();
        VerticalLayout content = new VerticalLayout();
         Label titulo = new Label("Buscar Crimen");
        titulo.addStyleName(Reindeer.LABEL_H2);
        content.addComponent(titulo);
        HorizontalLayout menu = new HorizontalLayout();
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        
        PopupDateField fecha1 = new PopupDateField();
        fecha1.setValue(result);
        fecha1.setImmediate(true);
        fecha1.setTimeZone(TimeZone.getTimeZone("UTC"));
        fecha1.setLocale(Locale.US);
        fecha1.setResolution(Resolution.MINUTE);
        fecha1.setRequired(true);
        menu.addComponent(fecha1);
        
         PopupDateField fecha2 = new PopupDateField();
        fecha2.setValue(new Date());
        fecha2.setImmediate(true);
        fecha2.setTimeZone(TimeZone.getTimeZone("UTC"));
        fecha2.setLocale(Locale.US);
        fecha2.setResolution(Resolution.MINUTE);
        fecha2.setRequired(true);
          menu.addComponent(fecha2);
     Button boton = new Button("Buscar");
        menu.addComponent(boton);
         Table table = new Table("Delitos");
          SQLContainer delitosTab = new Delitos().getDelitosData();
               SQLContainer categoriasTab = new Categorias().getCategoriasData();
               SQLContainer distritosTab = new Distritos().getDistritosData();
               delitosTab.addContainerFilter(new And (new Greater("DATE",fecha1.getValue()),new Less("DATE",fecha2.getValue())));
               
                
               table.setImmediate(true);
               
               delitosTab.addReference(categoriasTab, "CATEGORYKEY", "CATEGORYKEY");
               delitosTab.addReference(distritosTab, "DISTRICTKEY", "DISTRICTKEY");
               table.setContainerDataSource(delitosTab);
               table.addGeneratedColumn("CATEGORIA",  new ColumnGenerator() {
                        @Override
                        public Component generateCell(Table source, Object itemId,
                        Object columnId) {
                        Label label = new Label();
                        Item categoria = delitosTab.getReferencedItem(itemId,
                        categoriasTab);
                        Property property = categoria.getItemProperty("CATEGORYDESCRIPTION");
                        label.setValue( (String)property.getValue());
                        return label;
                        }
                    });
               
               table.addGeneratedColumn("DISTRITO",  new ColumnGenerator() {
                        @Override
                        public Component generateCell(Table source, Object itemId,
                        Object columnId) {
                        Label label = new Label();
                        Item distrito = delitosTab.getReferencedItem(itemId,
                        distritosTab);
                        Property property = distrito.getItemProperty("DISTRICTDESCRIPTION");
                        label.setValue( (String)property.getValue());
                        return label;
                        }
                    });
               table.setVisibleColumns(new String[] {"CATEGORIA","DISTRITO", "DESCRIPTION",
                "RESOLUTION", "ADDRESS", "INCIDENTTIME", "DATE"});
               
        
        
         boton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
            delitosTab.removeAllContainerFilters();
            delitosTab.addContainerFilter(new And (new Greater("DATE",fecha1.getValue()),new Less("DATE",fecha2.getValue())));
            table.setContainerDataSource(delitosTab);
                  table.setVisibleColumns(new String[] {"CATEGORIA","DISTRITO", "DESCRIPTION",
                "RESOLUTION", "ADDRESS", "INCIDENTTIME", "DATE"});
            }
        });
  
          Button botonM = new Button("Mapa");
          menu.addComponent(botonM);
          botonM.addClickListener(new Button.ClickListener(){
              @Override
            public void buttonClick(final Button.ClickEvent event) {
            
            final Window window = new Window("Mapa");
            window.setWidth(800.0f, Unit.PIXELS);
            final VerticalLayout content = new VerticalLayout();
            window.setContent(content);
            UI.getCurrent().addWindow(window);
            }
              
          });
         content.addComponent(menu);
      content.addComponent(table);
    
       
        
        frame.setContent(content);
        VerticalLayout layout = frame.iniciar(this);
        setContent(layout);
    }

    @WebServlet(value = {"/searchCrime/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = SearchCrime.class, productionMode = false)
    public static class searchServlet extends VaadinServlet {
    }
}