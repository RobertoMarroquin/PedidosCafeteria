package com.grupo10.pedidoscafeteria;

public class DetallePedido {
    private int iddetallepedido;
    private int idpedido;
    private int cantidad;
    private float subtotal;

    public DetallePedido(int iddetallepedido, int idpedido, int cantidad, float subtotal) {
        this.iddetallepedido = iddetallepedido;
        this.idpedido = idpedido;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DetallePedido() {
    }

    public int getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(int iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
