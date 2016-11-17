package com.itesm.services;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class Estadisticas extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Frame frame = new Frame();
        VerticalLayout content = new VerticalLayout();
         Label titulo = new Label("Estadisticas");
        titulo.addStyleName(Reindeer.LABEL_H2);
        content.addComponent(titulo);
        
        frame.setContent(content);
        VerticalLayout layout = frame.iniciar(this);
        setContent(layout);
    }

    @WebServlet(value = {"/estadisticas/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = Estadisticas.class, productionMode = false)
    public static class EstadisticasServler extends VaadinServlet {
    }
}
