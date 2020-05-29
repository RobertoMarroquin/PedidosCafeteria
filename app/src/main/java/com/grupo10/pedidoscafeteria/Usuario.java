package com.grupo10.pedidoscafeteria;

public class Usuario {
    private String nombreusuario;

    private String contrasena;
    private String usuario;


    public Usuario(String nombreusuario, String contrasena, String usuario) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreusuario = nombreusuario;
    }

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }
}
