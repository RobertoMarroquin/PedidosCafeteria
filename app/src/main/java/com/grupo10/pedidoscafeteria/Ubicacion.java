package com.grupo10.pedidoscafeteria;

public class Ubicacion {
    private String codubicacion;
    private String descubicacion;

    public Ubicacion(String codubicacion, String descubicacion) {
        this.codubicacion = codubicacion;
        this.descubicacion = descubicacion;
    }

    public Ubicacion() {
    }

    public String getCodubicacion() {
        return codubicacion;
    }

    public void setCodubicacion(String codubicacion) {
        this.codubicacion = codubicacion;
    }

    public String getDescubicacion() {
        return descubicacion;
    }

    public void setDescubicacion(String descubicacion) {
        this.descubicacion = descubicacion;
    }
}
