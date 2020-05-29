package com.grupo10.pedidoscafeteria;

public class Empleado {
    private String codempleado;
    private String codfacultad;
    private String codubicacion;
    private String codlocal;
    private String nomempleado;
    private String apeempleado;
    private String telempleado;

    public Empleado(String codempleado, String codfacultad, String codubicacion, String codlocal, String nomempleado, String apeempleado, String telempleado) {
        this.codempleado = codempleado;
        this.codfacultad = codfacultad;
        this.codubicacion = codubicacion;
        this.codlocal = codlocal;
        this.nomempleado = nomempleado;
        this.apeempleado = apeempleado;
        this.telempleado = telempleado;
    }

    public Empleado() {
    }

    public String getCodempleado() {
        return codempleado;
    }

    public void setCodempleado(String codempleado) {
        this.codempleado = codempleado;
    }

    public String getCodfacultad() {
        return codfacultad;
    }

    public void setCodfacultad(String codfacultad) {
        this.codfacultad = codfacultad;
    }

    public String getCodubicacion() {
        return codubicacion;
    }

    public void setCodubicacion(String codubicacion) {
        this.codubicacion = codubicacion;
    }

    public String getCodlocal() {
        return codlocal;
    }

    public void setCodlocal(String codlocal) {
        this.codlocal = codlocal;
    }

    public String getNomempleado() {
        return nomempleado;
    }

    public void setNomempleado(String nomempleado) {
        this.nomempleado = nomempleado;
    }

    public String getApeempleado() {
        return apeempleado;
    }

    public void setApeempleado(String apeempleado) {
        this.apeempleado = apeempleado;
    }

    public String getTelempleado() {
        return telempleado;
    }

    public void setTelempleado(String telempleado) {
        this.telempleado = telempleado;
    }
}

