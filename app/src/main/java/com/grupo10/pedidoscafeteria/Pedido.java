package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class Pedido {
    private int idpedido;
    private int idruta;
    private int idestadopedido;
    private int idlocal;
    private Date fechapedido;

    public Pedido(int idpedido, int idruta, int idestadopedido, int idlocal, Date fechapedido) {
        this.idpedido = idpedido;
        this.idruta = idruta;
        this.idestadopedido = idestadopedido;
        this.idlocal = idlocal;
        this.fechapedido = fechapedido;
    }

    public Pedido() {
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

    public int getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(int idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public int getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(int idlocal) {
        this.idlocal = idlocal;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }
}
