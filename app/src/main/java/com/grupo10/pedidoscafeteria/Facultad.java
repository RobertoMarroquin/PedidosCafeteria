package com.grupo10.pedidoscafeteria;

public class Facultad {
    private String codfacultad;
    private String nomfacultad;

    public Facultad(String codfacultad, String nomfacultad) {
        this.codfacultad = codfacultad;
        this.nomfacultad = nomfacultad;
    }

    public Facultad() {
    }

    public String getCodfacultad() {
        return codfacultad;
    }

    public void setCodfacultad(String codfacultad) {
        this.codfacultad = codfacultad;
    }

    public String getNomfacultad() {
        return nomfacultad;
    }

    public void setNomfacultad(String nomfacultad) {
        this.nomfacultad = nomfacultad;
    }
}
