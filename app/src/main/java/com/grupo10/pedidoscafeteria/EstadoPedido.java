package com.grupo10.pedidoscafeteria;

public class EstadoPedido {
    private int idestadopedido;
    private String Descestadopedido;

    public EstadoPedido(int idestadopedido, String descestadopedido) {
        this.idestadopedido = idestadopedido;
        Descestadopedido = descestadopedido;
    }

    public EstadoPedido() {
    }

    public int getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(int idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getDescestadopedido() {
        return Descestadopedido;
    }

    public void setDescestadopedido(String descestadopedido) {
        Descestadopedido = descestadopedido;
    }
}
