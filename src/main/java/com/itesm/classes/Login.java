/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.classes;

import com.vaadin.ui.UI;

/**
 *
 * @author emmanuelpaez
 */
public class Login {
    
    public boolean validate(String user, String password) {
        boolean flag = false;
    
             if (new com.itesm.DAO.Login().getData(user, password)==1) {
             flag = true;
        }
 
        return flag;
    }
    
}
