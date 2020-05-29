package com.grupo10.pedidoscafeteria;

public class DetalleProductoPrecio {
    private int idcambioprecio;
    private int idproducto;
    private float preciocambio;

    public DetalleProductoPrecio(int idcambioprecio, int idproducto, float preciocambio) {
        this.idcambioprecio = idcambioprecio;
        this.idproducto = idproducto;
        this.preciocambio = preciocambio;
    }

    public DetalleProductoPrecio() {
    }

    public int getIdcambioprecio() {
        return idcambioprecio;
    }

    public void setIdcambioprecio(int idcambioprecio) {
        this.idcambioprecio = idcambioprecio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public float getPreciocambio() {
        return preciocambio;
    }

    public void setPreciocambio(float preciocambio) {
        this.preciocambio = preciocambio;
    }
}
