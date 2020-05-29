package com.grupo10.pedidoscafeteria;

public class EncargadoLocal {
    private String codencargadolocal;
    private String nomencargadolocal;
    private String apeencargadolocal;
    private String telencargadolocal;

    public EncargadoLocal(String codencargadolocal, String nomencargadolocal, String apeencargadolocal, String telencargadolocal) {
        this.codencargadolocal = codencargadolocal;
        this.nomencargadolocal = nomencargadolocal;
        this.apeencargadolocal = apeencargadolocal;
        this.telencargadolocal = telencargadolocal;
    }

    public EncargadoLocal() {
    }

    public String getCodencargadolocal() {
        return codencargadolocal;
    }

    public void setCodencargadolocal(String codencargadolocal) {
        this.codencargadolocal = codencargadolocal;
    }

    public String getNomencargadolocal() {
        return nomencargadolocal;
    }

    public void setNomencargadolocal(String nomencargadolocal) {
        this.nomencargadolocal = nomencargadolocal;
    }

    public String getApeencargadolocal() {
        return apeencargadolocal;
    }

    public void setApeencargadolocal(String apeencargadolocal) {
        this.apeencargadolocal = apeencargadolocal;
    }

    public String getTelencargadolocal() {
        return telencargadolocal;
    }

    public void setTelencargadolocal(String telencargadolocal) {
        this.telencargadolocal = telencargadolocal;
    }
}
