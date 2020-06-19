package com.grupo10.pedidoscafeteria;

public class PedidosAsignados {
    private String idtrabajador;
    private int idpedido;

    public PedidosAsignados(String idtrabajador, int idpedido) {
        this.idtrabajador = idtrabajador;
        this.idpedido = idpedido;
    }

    public PedidosAsignados() {
    }

    public String getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(String idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }
}
