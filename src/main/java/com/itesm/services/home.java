package com.itesm.services;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.io.File;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class home extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Frame frame = new Frame();
        VerticalLayout content = new VerticalLayout();
        Label titulo = new Label("Bienvenido");
        titulo.addStyleName(Reindeer.LABEL_H1);
        content.addComponent(titulo);
        Label text = new Label("<p>Este proyecto tiene como proposito crear un sistema que permita la captura, gestión y modificacion de sucesos <br/>delictivos en una ciudad determinada, además crea un analisis estadistico-geográfico para encontrar patrones o tendencias </br>de tipos de crimenes y areas de pontencial riesgo.</p>", Label.CONTENT_TEXT.HTML);
        content.addComponent(text);
        HorizontalLayout carrusel = new HorizontalLayout();
        carrusel.setWidth("100%");
        carrusel.setMargin(new MarginInfo(false, true, false, true));
        carrusel.setSpacing(true);
        
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();       
        FileResource img1 = new FileResource(new File(basepath +"/WEB-INF/images/bar-chart.png"));
        Image image1 = new Image("", img1);
        image1.setHeight("80px");
        carrusel.addComponent(image1);
        
        FileResource img2 = new FileResource(new File(basepath +"/WEB-INF/images/map.png"));
        Image image2 = new Image("", img2);
        image2.setHeight("80px");
        carrusel.addComponent(image2);
        
        FileResource img3 = new FileResource(new File(basepath +"/WEB-INF/images/pie-chart.png"));
        Image image3 = new Image("", img3);
        image3.setHeight("80px");
        carrusel.addComponent(image3);
        
        FileResource img4 = new FileResource(new File(basepath +"/WEB-INF/images/burglar.png"));
        Image image4 = new Image("", img4);
        image4.setHeight("80px");
        carrusel.addComponent(image4);
        content.addComponent(carrusel);
        frame.setContent(content);
        VerticalLayout layout = frame.iniciar(this);
        setContent(layout);
    }
    
    

    @WebServlet(value = {"/home/*", "/VAADIN/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = home.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
