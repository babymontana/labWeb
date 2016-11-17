package com.itesm.services;

import com.itesm.classes.Login;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class Index extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        
        VerticalLayout layout = new VerticalLayout();
              Panel panel = new Panel("Login");
                panel.setHeight("400px");
                panel.setWidth("40%");
                
        final VerticalLayout form = new VerticalLayout();
        form.setWidth("500px");
        form.setMargin(true);
        form.addComponent(new Label("Usuario"));
        
        TextField usuario = new TextField();
        usuario.setImmediate(true);
        usuario.setInputPrompt("Escribe tu usuario.");
        usuario.setMaxLength(60);
        usuario.setRequired(true);
        form.addComponent(usuario);
        form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        form.addComponent(new Label("Password"));
        
        
        PasswordField password = new PasswordField();
        password.setImmediate(true);
        password.setMaxLength(60);
        password.setRequired(true);
        form.addComponent(password);
        
          Button boton = new Button("Entrar");
        boton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
               
                if(usuario.isValid() && password.isValid()){
                    
                    Login log = new Login();
                    if(log.validate(usuario.getValue(), password.getValue())){
                        Notification.show("Bienvenido.",Type.HUMANIZED_MESSAGE);  
                        getUI().getPage().setLocation("home");
                        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("valido", true);
                    }else{
                       Notification.show("Usuario o contraseña incorrecto.",Type.ERROR_MESSAGE);  
                    }
                    
                }else{
                     Notification.show("Porfavor llena los campos obligatorios.",Type.ERROR_MESSAGE);
                }
            }
        });
        form.addComponent(new Label("<br/>",Label.CONTENT_XHTML));
        form.addComponent(boton);
        
        panel.setContent(form);
        layout.addComponent(panel);
        layout.setSizeFull();
        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @WebServlet(value = {"/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = Index.class, productionMode = true)
    public static class IndexServlet extends VaadinServlet {
    }
}
