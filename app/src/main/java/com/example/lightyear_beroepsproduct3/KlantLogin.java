package com.example.lightyear_beroepsproduct3;

public class KlantLogin {
    private String mldrs, vrnm, chtrnm, wchtwrd, tlfnnmmr;

    public KlantLogin(String emailadres, String voornaam, String achternaam, String wachtwoord, String telefoonnummer) {
        this.mldrs = emailadres;
        this.vrnm = voornaam;
        this.chtrnm = achternaam;
        this.wchtwrd = wachtwoord;
        this.tlfnnmmr = telefoonnummer;
    }

    public String getMldrs() {
        return mldrs;
    }

    public void setMldrs(String mldrs) {
        this.mldrs = mldrs;
    }

    public String getVrnm() {
        return vrnm;
    }

    public void setVrnm(String vrnm) {
        this.vrnm = vrnm;
    }

    public String getChtrnm() {
        return chtrnm;
    }

    public void setChtrnm(String chtrnm) {
        this.chtrnm = chtrnm;
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
