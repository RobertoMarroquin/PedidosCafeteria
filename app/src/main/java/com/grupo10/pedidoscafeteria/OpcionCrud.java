package com.grupo10.pedidoscafeteria;

public class OpcionCrud {
    private int idopcion;
    private String desopcion;
    private int numcrud;

    public OpcionCrud(int idopcion, String desopcion, int numcrud) {
        this.idopcion = idopcion;
        this.desopcion = desopcion;
        this.numcrud = numcrud;
    }

    public OpcionCrud() {
    }

    public int getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(int idopcion) {
        this.idopcion = idopcion;
    }

    public String getDesopcion() {
        return desopcion;
    }

    public void setDesopcion(String desopcion) {
        this.desopcion = desopcion;
    }

    public int getNumcrud() {
        return numcrud;
    }

    public void setNumcrud(int numcrud) {
        this.numcrud = numcrud;
    }
}
