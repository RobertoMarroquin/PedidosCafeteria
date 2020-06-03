package com.grupo10.pedidoscafeteria;

public class Producto {
    private String codproducto;
    private String codmenu;
    private String nombreproducto;
    private float preciounitario;

    public Producto(String codproducto, String codmenu, String nombreproducto, float preciounitario) {
        this.codproducto = codproducto;
        this.codmenu = codmenu;
        this.nombreproducto = nombreproducto;
        this.preciounitario = preciounitario;
    }

    public Producto() {
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public String getCodmenu() {
        return codmenu;
    }

    public void setCodmenu(String codmenu) {
        this.codmenu = codmenu;
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
