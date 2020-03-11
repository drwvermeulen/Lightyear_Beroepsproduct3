package com.example.lightyear_beroepsproduct3;

public class KoppelKlantConfig {
    private String mldrs;
    private Integer cnfgrtnmmr;

    public KoppelKlantConfig(String emailadres, Integer configuratienummer) {
        this.mldrs = emailadres;
        this.cnfgrtnmmr = configuratienummer;
    }

    public String getMldrs() {
        return mldrs;
    }

    public void setMldrs(String mldrs) {
        this.mldrs = mldrs;
    }

    public Integer getCnfgrtnmmr() {
        return cnfgrtnmmr;
    }

    public void setCnfgrtnmmr(Integer cnfgrtnmmr) {
        this.cnfgrtnmmr = cnfgrtnmmr;
    }
}
