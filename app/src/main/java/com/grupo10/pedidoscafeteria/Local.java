package com.grupo10.pedidoscafeteria;

public class Local {
    private String codlocal;
    private String codencargadolocal;
    private String nombrelocal;


    public Local(String codlocal, String codencargadolocal, String nombrelocal) {
        this.codlocal = codlocal;
        this.codencargadolocal = codencargadolocal;
        this.nombrelocal = nombrelocal;
    }

    public Local() {
    }

    public String getCodlocal() {
        return codlocal;
    }

    public void setCodlocal(String codlocal) {
        this.codlocal = codlocal;
    }

    public String getCodencargadolocal() {
        return codencargadolocal;
    }

    public void setCodencargadolocal(String codencargadolocal) {
        this.codencargadolocal = codencargadolocal;
    }

    public String getNombrelocal() {
        return nombrelocal;
    }

    public void setNombrelocal(String nombrelocal) {
        this.nombrelocal = nombrelocal;
    }
}
