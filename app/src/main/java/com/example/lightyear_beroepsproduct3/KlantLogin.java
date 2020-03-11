package com.example.lightyear_beroepsproduct3;

public class KlantLogin {
    private String mldrs, nm, wchtwrd, tlfnnmmr;

    public KlantLogin(String emailadres, String naam, String wachtwoord, String telefoonnummer) {
        this.mldrs = emailadres;
        this.nm = naam;
        this.wchtwrd = wachtwoord;
        this.tlfnnmmr = telefoonnummer;
    }

    public String getMldrs() {
        return mldrs;
    }

    public void setMldrs(String mldrs) {
        this.mldrs = mldrs;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getWchtwrd() {
        return wchtwrd;
    }

    public void setWchtwrd(String wchtwrd) {
        this.wchtwrd = wchtwrd;
    }

    public String getTlfnnmmr() {
        return tlfnnmmr;
    }

    public void setTlfnnmmr(String tlfnnmmr) {
        this.tlfnnmmr = tlfnnmmr;
    }
}
