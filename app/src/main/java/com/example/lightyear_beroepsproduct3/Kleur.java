package com.example.lightyear_beroepsproduct3;

import java.util.HashMap;
import java.util.Map;

public enum Kleur {
    KleurZwart("Zwart", 0),
    KleurWit("Wit", 1),
    KleurRood("Rood", 2),
    KleurBlauw("Blauw", 3);

    private String stringValue;
    private int intValue;
    private Kleur(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    public Double getPrijs() {
        Double prijs = 0.00;
        switch (intValue) {
            case 0:
                prijs = 1000.00;
                break;
            case 1:
                prijs = 2000.00;
                break;
            case 2:
                prijs = 3000.00;
                break;
            case 3:
                prijs = 4000.00;
                break;
        }
        return prijs;
    }

    @Override
    public String toString() {
        return stringValue;
    }
    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, Kleur> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Kleur klr : Kleur.values())
        {
            lookup.put(klr.toString(), klr);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Kleur getKleur(String klr)
    {
        return lookup.get(klr);
    }

}
