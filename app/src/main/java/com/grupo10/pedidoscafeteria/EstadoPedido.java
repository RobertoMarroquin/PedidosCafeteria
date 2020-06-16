package com.grupo10.pedidoscafeteria;

public class EstadoPedido {
    private String codestadopedido;
    private String Descestadopedido;

    public EstadoPedido(String codestadopedido, String descestadopedido) {
        this.codestadopedido = codestadopedido;
        this.Descestadopedido = descestadopedido;
    }

    public EstadoPedido() {
    }

    public String getCodestadopedido() {
        return codestadopedido;
    }

    public void setCodestadopedido(String codestadopedido) {
        this.codestadopedido = codestadopedido;
    }

    public String getDescestadopedido() {
        return Descestadopedido;
    }

    public void setDescestadopedido(String descestadopedido) {
        Descestadopedido = descestadopedido;
    }
}
