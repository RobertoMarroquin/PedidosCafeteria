package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class RutaPedido {
    private int idruta;
    private String codrepartidor;
    private String inicioruta;
    private String finruta;

    public RutaPedido(int idruta, String codrepartidor, String inicioruta, String finruta) {
        this.idruta = idruta;
        this.codrepartidor = codrepartidor;
        this.inicioruta = inicioruta;
        this.finruta = finruta;
    }

    public RutaPedido() {
    }

    public int getIdruta() {
        return idruta;
    }

    public void setIdruta(int idruta) {
        this.idruta = idruta;
    }

    public String getCodrepartidor() {
        return codrepartidor;
    }

    public void setCodrepartidor(String codrepartidor) {
        this.codrepartidor = codrepartidor;
    }

    public String getInicioruta() {
        return inicioruta;
    }

    public void setInicioruta(String inicioruta) {
        this.inicioruta = inicioruta;
    }

    public String getFinruta() {
        return finruta;
    }

    public void setFinruta(String finruta) {
        this.finruta = finruta;
    }
}
