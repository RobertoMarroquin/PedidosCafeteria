package com.grupo10.pedidoscafeteria;

public class DetalleProductoEmpleado {
    private String idtrabajador;
    private int idpedido;
    private String idproducto;
    private int cantidadpedido;

    public DetalleProductoEmpleado(String idtrabajador, int idpedido, String idproducto, int cantidadpedido) {
        this.idtrabajador = idtrabajador;
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidadpedido = cantidadpedido;
    }

    public DetalleProductoEmpleado() {
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

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidadpedido() {
        return cantidadpedido;
    }

    public void setCantidadpedido(int cantidadpedido) {
        this.cantidadpedido = cantidadpedido;
    }
}
