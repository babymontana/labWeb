package com.itesm.services;

import com.itesm.classes.Categorias;
import com.itesm.classes.Delito;
import com.itesm.classes.Delitos;
import com.itesm.classes.Distritos;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


@Theme("mytheme")
public class AddCrime extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Frame frame = new Frame();
        HorizontalLayout content = new HorizontalLayout();
        VerticalLayout lay = new VerticalLayout();
        FormLayout form = new FormLayout();
         Label titulo = new Label("Nuevo Crimen");
        titulo.addStyleName(Reindeer.LABEL_H2);
        content.addComponent(titulo);
        content.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        content.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        content.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        ComboBox categorias = new ComboBox("Selecciona una categoria");
        categorias.setContainerDataSource(new Categorias().getCategoriasData());
        
        categorias.setItemCaptionPropertyId("CATEGORYDESCRIPTION");
        categorias.setNullSelectionAllowed(false);
        categorias.setImmediate(true);
        form.addComponent(categorias);
        form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        
        ComboBox distritos = new ComboBox("Selecciona una distrito");
        distritos.setContainerDataSource(new Distritos().getDistritosData());
        
        distritos.setItemCaptionPropertyId("DISTRICTDESCRIPTION");
        distritos.setNullSelectionAllowed(false);
        distritos.setImmediate(true);
        form.addComponent(distritos);
        form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
         FormLayout form2 = new FormLayout();
        TextArea descripcion = new TextArea();
        descripcion.setImmediate(true);
        descripcion.setColumns(20);
        descripcion.setInputPrompt("Descripción.");
        descripcion.setMaxLength(100);
        descripcion.setRequired(true);
         form.addComponent(descripcion);
         form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
         
        InlineDateField fecha = new InlineDateField();
        fecha.setValue(new Date());
        fecha.setImmediate(true);
        fecha.setTimeZone(TimeZone.getTimeZone("UTC"));
        fecha.setLocale(Locale.US);
        fecha.setResolution(Resolution.MINUTE);
        fecha.setRequired(true);
        form2.addComponent(fecha);
          
        TextArea resolucion = new TextArea();
        resolucion.setColumns(20);
        resolucion.setImmediate(true);
        resolucion.setInputPrompt("Resolución.");
        resolucion.setMaxLength(100);
        resolucion.setRequired(true);
        form.addComponent(resolucion);
        form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        
        TextArea direccion = new TextArea();
        direccion.setImmediate(true);
        direccion.setColumns(20);
        direccion.setInputPrompt("Dirección.");
        direccion.setMaxLength(100);
        direccion.setRequired(true);
        form.addComponent(direccion);
        
                
        Button boton = new Button("Agregar");
        form2.addComponent(boton);
        content.addComponent(form);
        content.addComponent(form2);
        
        PropertysetItem myfields = new PropertysetItem();

        myfields.addItemProperty("categoria", new ObjectProperty(""));     
        myfields.addItemProperty("distrito", new ObjectProperty(""));
        myfields.addItemProperty("descripcion", new ObjectProperty(""));
        myfields.addItemProperty("resolucion", new ObjectProperty(""));
        myfields.addItemProperty("direccion", new ObjectProperty(""));
        
        FieldGroup fieldGroup = new FieldGroup(myfields);

        fieldGroup.bind(categorias, "categoria");
        fieldGroup.bind(distritos, "distrito");
        fieldGroup.bind(descripcion, "descripcion");
        fieldGroup.bind(resolucion, "resolucion");
        fieldGroup.bind(direccion, "direccion");


        
        boton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                
                try{
                    categorias.validate();
                    distritos.validate();
                    descripcion.validate();
                    resolucion.validate();
                    direccion.validate();
                    fecha.validate();
                  Delito delito = new Delito(Integer.parseInt(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("id").toString()),Integer.parseInt(categorias.getValue().toString()),Integer.parseInt(distritos.getValue().toString()),descripcion.getValue(),resolucion.getValue(),direccion.getValue(),fecha.getValue());
    ;                   Delitos delitos = new Delitos();
                 
                    
                    try {
                        
                       
                        delitos.addDelito(delito);
                        Notification.show("Registro agregado exitosamente.",Notification.Type.HUMANIZED_MESSAGE);
                        categorias.setValue(null);
                        distritos.setValue(null);
                        descripcion.setValue("");
                        resolucion.setValue("");
                        direccion.setValue("");
                        fecha.setValue(new Date());

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                       Notification.show("Lo sentimos ha ocurrido un error.",Notification.Type.ERROR_MESSAGE);
                    }
                    
                    
                    
                }catch (InvalidValueException e){
                     Notification.show("Porfavor llene los campos obligatorios.",Notification.Type.ERROR_MESSAGE); 
                }

               
               
            }
        });

        lay.addComponent(content);
        
        frame.setContent(lay);
        VerticalLayout layout = frame.iniciar(this);
        setContent(layout);
    }

    @WebServlet(value = {"/addCrime/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = AddCrime.class, productionMode = false)
    public static class CrimeServlet extends VaadinServlet {
    }
}
