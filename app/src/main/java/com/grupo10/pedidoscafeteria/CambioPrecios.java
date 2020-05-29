package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class CambioPrecios {
    private int idcambioprecio;
    private Date fechadesdecambio;
    private Date fechahastacambio;

    public CambioPrecios(int idcambioprecio, Date fechadesdecambio, Date fechahastacambio) {
        this.idcambioprecio = idcambioprecio;
        this.fechadesdecambio = fechadesdecambio;
        this.fechahastacambio = fechahastacambio;
    }

    public CambioPrecios() {
    }

    public int getIdcambioprecio() {
        return idcambioprecio;
    }

    public void setIdcambioprecio(int idcambioprecio) {
        this.idcambioprecio = idcambioprecio;
    }

    public Date getFechadesdecambio() {
        return fechadesdecambio;
    }

    public void setFechadesdecambio(Date fechadesdecambio) {
        this.fechadesdecambio = fechadesdecambio;
    }

    public Date getFechahastacambio() {
        return fechahastacambio;
    }

    public void setFechahastacambio(Date fechahastacambio) {
        this.fechahastacambio = fechahastacambio;
    }
}
