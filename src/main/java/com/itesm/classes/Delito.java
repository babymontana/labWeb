
package com.itesm.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Delito {
    
    private int ADMINISTRADORKEY;
    private int CATEGORYKEY;
    private int DISTRICTKEY;
    private String DESCRIPTION;
    private String RESOLUTION;
    private String ADDRESS;
    private String INCIDENTTIME;
    private Date date;
    private String x;
    private String y;

    public Delito(int ADMINISTRADORKEY, int CATEGORYKEY, int DISTRICTKEY, String DESCRIPTION, String RESOLUTION, String ADDRESS, Date fecha) {
        this.ADMINISTRADORKEY = ADMINISTRADORKEY;
        this.CATEGORYKEY = CATEGORYKEY;
        this.DISTRICTKEY = DISTRICTKEY;
        this.DESCRIPTION = DESCRIPTION;
        this.RESOLUTION = RESOLUTION;
        this.ADDRESS = ADDRESS;
        this.date=fecha;
        this.INCIDENTTIME=new SimpleDateFormat("HH:mm:ss").format(fecha);
    }

    public int getADMINISTRADORKEY() {
        return ADMINISTRADORKEY;
    }

    public void setADMINISTRADORKEY(int ADMINISTRADORKEY) {
        this.ADMINISTRADORKEY = ADMINISTRADORKEY;
    }

    public int getCATEGORYKEY() {
        return CATEGORYKEY;
    }

    public void setCATEGORYKEY(int CATEGORYKEY) {
        this.CATEGORYKEY = CATEGORYKEY;
    }

    public int getDISTRICTKEY() {
        return DISTRICTKEY;
    }

    public void setDISTRICTKEY(int DISTRICTKEY) {
        this.DISTRICTKEY = DISTRICTKEY;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getRESOLUTION() {
        return RESOLUTION;
    }

    public void setRESOLUTION(String RESOLUTION) {
        this.RESOLUTION = RESOLUTION;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getINCIDENTTIME() {
        return INCIDENTTIME;
    }

    public void setINCIDENTTIME(String INCIDENTTIME) {
        this.INCIDENTTIME = INCIDENTTIME;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
}
