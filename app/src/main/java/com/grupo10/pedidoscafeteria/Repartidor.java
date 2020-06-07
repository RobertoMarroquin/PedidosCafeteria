package com.grupo10.pedidoscafeteria;

public class Repartidor {
    private String codrepartidor;
    private String nomrepartidor;
    private String aperepartidor;
    private String telrepartidor;

    public Repartidor(String codrepartidor, String nomrepartidor, String aperepartidor, String telrepartidor) {
        this.codrepartidor = codrepartidor;
        this.nomrepartidor = nomrepartidor;
        this.aperepartidor = aperepartidor;
        this.telrepartidor = telrepartidor;
    }

    public Repartidor() {
    }

    public String getCodrepartidor() {
        return codrepartidor;
    }

    public void setCodrepartidor(String codrepartidor) {
        this.codrepartidor = codrepartidor;
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
