package com.grupo10.pedidoscafeteria;

public class Producto {
    private int idproducto;
    private int idmenu;
    private String nombreproducto;
    private float preciounitario;

    public Producto(int idproducto, int idmenu, String nombreproducto, float preciounitario) {
        this.idproducto = idproducto;
        this.idmenu = idmenu;
        this.nombreproducto = nombreproducto;
        this.preciounitario = preciounitario;
    }

    public Producto() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public float getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(float preciounitario) {
        this.preciounitario = preciounitario;
    }
}
