package com.example.lightyear_beroepsproduct3;

import java.io.Serializable;

public class GeconfigureerdeLightyear implements Serializable {
    private Integer cnfgrtnmmr;
    private Model mdl;
    private Kleur klr;
    private Lak lk;
    private Velg vlg;

    //Deze methode kijkt welke class geinitialiseerd moet worden
    public static GeconfigureerdeLightyear construct(Model model) {
        GeconfigureerdeLightyear geconfigureerdeLightyear = null;

        switch (model) {
            case LightyearOne:
                geconfigureerdeLightyear = new GeconfigureerdeLightyear(model);
                break;

            case LightyearOnePioneer:
                geconfigureerdeLightyear = new GeconfigureerdeLightyearPioneerEdition(model);
                break;
        }
        return geconfigureerdeLightyear;
    }

    public GeconfigureerdeLightyear(Model model) {
        this.mdl = model;
    }

    public Integer getCnfgrtnmmr() {
        return cnfgrtnmmr;
    }

    public void setCnfgrtnmmr(Integer cnfgrtnmmr) {
        this.cnfgrtnmmr = cnfgrtnmmr;
    }

    public Model getMdl() {
        return mdl;
    }

    public void setMdl(Model mdl) {
        this.mdl = mdl;
    }

    public Kleur getKlr() {
        return klr;
    }

    public void setKlr(Kleur klr) {
        this.klr = klr;
    }

    public Lak getLk() {
        return lk;
    }

    public void setLk(Lak lk) {
        this.lk = lk;
    }

    public Velg getVlg() {
        return vlg;
    }

    public void setVlg(Velg vlg) {
        this.vlg = vlg;
    }
}
