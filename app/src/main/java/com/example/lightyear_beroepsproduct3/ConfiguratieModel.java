package com.example.lightyear_beroepsproduct3;

public class ConfiguratieModel {
    private Integer cnfgrtnmmr, pnrdtn;
    private String mdl, klr, lk, vlg;

    public ConfiguratieModel(Integer configuratienummer, String model, String kleur, String lak, String velg, Integer pioneeredition) {
        this.cnfgrtnmmr = configuratienummer;
        this.mdl = model;
        this.klr = kleur;
        this.lk = lak;
        this.vlg = velg;
        this.pnrdtn = pioneeredition;
    }

    public Integer getCnfgrtnmmr() {
        return cnfgrtnmmr;
    }

    public void setCnfgrtnmmr(Integer cnfgrtnmmr) {
        this.cnfgrtnmmr = cnfgrtnmmr;
    }

    public Integer getPnrdtn() {
        return pnrdtn;
    }

    public void setPnrdtn(Integer pnrdtn) {
        this.pnrdtn = pnrdtn;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getKlr() {
        return klr;
    }

    public void setKlr(String klr) {
        this.klr = klr;
    }

    public String getLk() {
        return lk;
    }

    public void setLk(String lk) {
        this.lk = lk;
    }

    public String getVlg() {
        return vlg;
    }

    public void setVlg(String vlg) {
        this.vlg = vlg;
    }
}
