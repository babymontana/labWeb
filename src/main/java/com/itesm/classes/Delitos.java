
package com.itesm.classes;

import com.itesm.DAO.DelitosDAO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;



public class Delitos {
    
    private SQLContainer delitosData;
    
     public Delitos(){
       
       DelitosDAO data = new DelitosDAO();
       delitosData = data.getContainer();
      
    }
     
    public String[] coords (String address){
         String[] coord = new String[2];
         coord[0]="0.0";
         coord[1]="0.0";
        try {
            HttpResponse<String> response = Unirest.get("https://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(address,"UTF-8")+"&key=AIzaSyAsJw-cS7yiVQ3He3vfgFxUZ2_5LgUhVsM")
                    .header("authorization", "Basic MzU5ZjdjOGYtOTdiMi00ODYzLWI2NzctZjYyYWNkMzQwMjg5OlJGTVd3b1djaUtYQw==")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "e716799e-d0da-2eee-dc45-1651b699773a")
                    .asString();
           
             JSONParser parser = new JSONParser();
              Object obj = parser.parse(response.getBody());
               JSONObject obj2 = (JSONObject)obj;
               JSONArray results = (JSONArray)obj2.get("results");
               JSONObject resultsData = (JSONObject)results.get(0);
               JSONObject geometry = (JSONObject)resultsData.get("geometry");
               JSONObject location = (JSONObject)geometry.get("location");
               coord[0]=location.get("lat").toString();
               coord[1]=location.get("lng").toString();
               
            
        } catch (UnirestException ex) {
            Logger.getLogger(Delitos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Delitos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Delitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coord;
    }
     
    public void addDelito(Delito delito) throws SQLException{
        DelitosDAO data = new DelitosDAO();
        SQLContainer delitos = data.getContainer();
        Object newDelito = delitos.addItem();
        
       String[] coord=coords(delito.getADDRESS());
       delitos.getContainerProperty(newDelito , "ADMINISTRATORKEY").setValue(delito.getADMINISTRADORKEY());
       delitos.getContainerProperty(newDelito , "CATEGORYKEY").setValue(delito.getADMINISTRADORKEY());
       delitos.getContainerProperty(newDelito , "DISTRICTKEY").setValue(delito.getDISTRICTKEY());
       delitos.getContainerProperty(newDelito , "DESCRIPTION").setValue(delito.getDESCRIPTION());
       delitos.getContainerProperty(newDelito , "RESOLUTION").setValue(delito.getRESOLUTION());
       delitos.getContainerProperty(newDelito , "ADDRESS").setValue(delito.getADDRESS());
       delitos.getContainerProperty(newDelito , "INCIDENTTIME").setValue(delito.getINCIDENTTIME());
       delitos.getContainerProperty(newDelito , "DATE").setValue(delito.getDate());
        delitos.getContainerProperty(newDelito , "X").setValue(coord[0]);
        delitos.getContainerProperty(newDelito , "Y").setValue(coord[1]);
       delitos.commit();
    }

    
}
