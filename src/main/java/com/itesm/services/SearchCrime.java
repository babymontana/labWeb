/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.services;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
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
        
        frame.setContent(content);
        VerticalLayout layout = frame.iniciar(this);
        setContent(layout);
    }

    @WebServlet(value = {"/searchCrime/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = SearchCrime.class, productionMode = false)
    public static class searchServlet extends VaadinServlet {
    }
}