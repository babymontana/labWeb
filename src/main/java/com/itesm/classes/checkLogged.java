/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itesm.classes;

import static org.atmosphere.annotation.AnnotationUtil.logger;

/**
 *
 * @author emmanuelpaez
 */
public class checkLogged {
    public boolean validate(Object valido){
       boolean flag = false;
       try{
           flag = (boolean)valido;
       }catch(Exception exc) {
        logger.error("No hay variable de session");
   }
       
       return flag;
    }
}
