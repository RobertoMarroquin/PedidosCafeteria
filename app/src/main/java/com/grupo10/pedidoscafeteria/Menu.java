package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class Menu {
    private int idmenu;
    private int idlocal;
    private float preciomenu;
    private Date fechadesdemenu;
    private Date fechahastamenu;

    public Menu(int idmenu, int idlocal, float preciomenu, Date fechadesdemenu, Date fechahastamenu) {
        this.idmenu = idmenu;
        this.idlocal = idlocal;
        this.preciomenu = preciomenu;
        this.fechadesdemenu = fechadesdemenu;
        this.fechahastamenu = fechahastamenu;
    }

    public Menu() {
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(int idlocal) {
        this.idlocal = idlocal;
    }

    public float getPreciomenu() {
        return preciomenu;
    }

    public void setPreciomenu(float preciomenu) {
        this.preciomenu = preciomenu;
    }

    public Date getFechadesdemenu() {
        return fechadesdemenu;
    }

    public void setFechadesdemenu(Date fechadesdemenu) {
        this.fechadesdemenu = fechadesdemenu;
    }

    public Date getFechahastamenu() {
        return fechahastamenu;
    }

    public void setFechahastamenu(Date fechahastamenu) {
        this.fechahastamenu = fechahastamenu;
    }
}
