package com.grupo10.pedidoscafeteria;

public class DetalleProductoEmpleado {
    private int idtrabajador;
    private int idpedido;
    private int idproducto;
    private int cantidadpedido;

    public DetalleProductoEmpleado(int idtrabajador, int idpedido, int idproducto, int cantidadpedido) {
        this.idtrabajador = idtrabajador;
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidadpedido = cantidadpedido;
    }

    public DetalleProductoEmpleado() {
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

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidadpedido() {
        return cantidadpedido;
    }

    public void setCantidadpedido(int cantidadpedido) {
        this.cantidadpedido = cantidadpedido;
    }
}
