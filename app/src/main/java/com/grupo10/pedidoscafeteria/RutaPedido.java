package com.grupo10.pedidoscafeteria;

import java.util.Date;

public class RutaPedido {
    private int idruta;
    private int idrepartidor;
    private Date inicioruta;
    private Date finruta;

    public RutaPedido(int idruta, int idrepartidor, Date inicioruta, Date finruta) {
        this.idruta = idruta;
        this.idrepartidor = idrepartidor;
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

    public int getIdrepartidor() {
        return idrepartidor;
    }

    public void setIdrepartidor(int idrepartidor) {
        this.idrepartidor = idrepartidor;
    }

    public Date getInicioruta() {
        return inicioruta;
    }

    public void setInicioruta(Date inicioruta) {
        this.inicioruta = inicioruta;
    }

    public Date getFinruta() {
        return finruta;
    }

    public void setFinruta(Date finruta) {
        this.finruta = finruta;
    }
}
