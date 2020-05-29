package com.grupo10.pedidoscafeteria;

public class Local {
    private int idlocal;
    private int idencargadolocal;
    private String nombrelocal;

    public Local(int idlocal, int idencargadolocal, String nombrelocal) {
        this.idlocal = idlocal;
        this.idencargadolocal = idencargadolocal;
        this.nombrelocal = nombrelocal;
    }

    public Local() {
    }

    public int getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(int idlocal) {
        this.idlocal = idlocal;
    }

    public int getIdencargadolocal() {
        return idencargadolocal;
    }

    public void setIdencargadolocal(int idencargadolocal) {
        this.idencargadolocal = idencargadolocal;
    }

    public String getNombrelocal() {
        return nombrelocal;
    }

    public void setNombrelocal(String nombrelocal) {
        this.nombrelocal = nombrelocal;
    }
}
