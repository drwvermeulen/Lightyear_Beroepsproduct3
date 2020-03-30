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

            case LightyearPioneer:
                geconfigureerdeLightyear = new GeconfigureerdeLightyearPioneerEdition(model);
                break;
        }
        return geconfigureerdeLightyear;
    }

    public GeconfigureerdeLightyear(Model model) {
        this.mdl = model;
    }

    public int getImageResource() {
        int imageResource = R.drawable.lightyearone_wit;
        if(klr != null) {
            switch (klr) {
                case KleurZwart:
                    imageResource = R.drawable.lightyearone_zwart;
                    break;
                case KleurWit:
                    imageResource = R.drawable.lightyearone_wit;
                    break;
                case KleurRood:
                    imageResource = R.drawable.lightyearone_rood;
                    break;
                case KleurBlauw:
                    imageResource = R.drawable.lightyearone_blauw;
                    break;
            }
        }
        return imageResource;
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

    public String getFormattedConfig() {
        return String.format("CONFIG%05d", cnfgrtnmmr);
    }

    public Double berekenPrijs() {
        Double prijs = 0.00;

        prijs += mdl.getPrijs();

        if (klr != null) {
            prijs += klr.getPrijs();
        }

        if (lk != null) {
            prijs += lk.getPrijs();
        }

        if (vlg != null) {
            prijs += vlg.getPrijs();
        }

        return prijs;
    }
}
