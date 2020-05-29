package com.grupo10.pedidoscafeteria;

public class PedidosAsignados {
    private int idtrabajador;
    private int idpedido;

    public PedidosAsignados(int idtrabajador, int idpedido) {
        this.idtrabajador = idtrabajador;
        this.idpedido = idpedido;
    }

    public PedidosAsignados() {
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }
}
