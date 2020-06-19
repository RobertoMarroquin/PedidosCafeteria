package com.grupo10.pedidoscafeteria;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {
    private int idpedido;
    private int idruta;
    private String idestadopedido;
    private String codlocal;
    private String fechapedido;

    public Pedido() {
    }

    public Pedido(int idpedido, int idruta, String idestadopedido, String codlocal, String fechapedido) {
        this.idpedido = idpedido;
        this.idruta = idruta;
        this.idestadopedido = idestadopedido;
        this.codlocal = codlocal;
        this.fechapedido = fechapedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdruta() {
        return idruta;
    }

    public void setIdruta(int idruta) {
        this.idruta = idruta;
    }

    public String getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(String idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getCodlocal() {
        return codlocal;
    }

    public void setCodlocal(String codlocal) {
        this.codlocal = codlocal;
    }

    public String getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(String fechapedido) {
        this.fechapedido = fechapedido;
    }
}

