package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class Menu {
    private String codmenu;
    private String codlocal;
    private float preciomenu;
    private String fechadesdemenu;
    private String fechahastamenu;

    public Menu(String codmenu, String codlocal, float preciomenu, String fechadesdemenu, String fechahastamenu) {
        this.codmenu = codmenu;
        this.codlocal = codlocal;
        this.preciomenu = preciomenu;
        this.fechadesdemenu = fechadesdemenu;
        this.fechahastamenu = fechahastamenu;
    }

    public Menu() {
    }

    public String getCodmenu() {
        return codmenu;
    }

    public void setCodmenu(String codmenu) {
        this.codmenu = codmenu;
    }

    public String getCodlocal() {
        return codlocal;
    }

    public void setCodlocal(String codlocal) {
        this.codlocal = codlocal;
    }

    public float getPreciomenu() {
        return preciomenu;
    }

    public void setPreciomenu(float preciomenu) {
        this.preciomenu = preciomenu;
    }

    public String getFechadesdemenu() {
        return fechadesdemenu;
    }

    public void setFechadesdemenu(String fechadesdemenu) {
        this.fechadesdemenu = fechadesdemenu;
    }

    public String getFechahastamenu() {
        return fechahastamenu;
    }

    public void setFechahastamenu(String fechahastamenu) {
        this.fechahastamenu = fechahastamenu;
    }
}
