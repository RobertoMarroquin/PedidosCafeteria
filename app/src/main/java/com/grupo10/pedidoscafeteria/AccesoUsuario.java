package com.grupo10.pedidoscafeteria;

public class AccesoUsuario {
    private String usuario;
    private int idopcion;

    public AccesoUsuario(String usuario, int idopcion) {
        this.usuario = usuario;
        this.idopcion = idopcion;
    }

    public AccesoUsuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(int idopcion) {
        this.idopcion = idopcion;
    }
}
