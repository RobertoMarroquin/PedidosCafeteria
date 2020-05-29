package com.grupo10.pedidoscafeteria;

public class Repartidor {
    private int idrepartidor;
    private String nomrepartidor;
    private String aperepartidor;
    private String telrepartidor;

    public Repartidor(int idrepartidor, String nomrepartidor, String aperepartidor, String telrepartidor) {
        this.idrepartidor = idrepartidor;
        this.nomrepartidor = nomrepartidor;
        this.aperepartidor = aperepartidor;
        this.telrepartidor = telrepartidor;
    }

    public Repartidor() {
    }

    public int getIdrepartidor() {
        return idrepartidor;
    }

    public void setIdrepartidor(int idrepartidor) {
        this.idrepartidor = idrepartidor;
    }

    public String getNomrepartidor() {
        return nomrepartidor;
    }

    public void setNomrepartidor(String nomrepartidor) {
        this.nomrepartidor = nomrepartidor;
    }

    public String getAperepartidor() {
        return aperepartidor;
    }

    public void setAperepartidor(String aperepartidor) {
        this.aperepartidor = aperepartidor;
    }

    public String getTelrepartidor() {
        return telrepartidor;
    }

    public void setTelrepartidor(String telrepartidor) {
        this.telrepartidor = telrepartidor;
    }
}
