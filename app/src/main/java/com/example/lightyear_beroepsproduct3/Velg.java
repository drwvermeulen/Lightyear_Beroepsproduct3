package com.example.lightyear_beroepsproduct3;

import java.util.HashMap;
import java.util.Map;

public enum Velg {
    Velgen16("16inch Velgen", 0),
    Velgen17("17inch Velgen", 1),
    Velgen18("18inch Velgen", 2);

    private String stringValue;
    private int intValue;
    private Velg(String toString, int value) {
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
        }
        return prijs;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, Velg> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Velg vlg : Velg.values())
        {
            lookup.put(vlg.toString(), vlg);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Velg getVelg(String vlg)
    {
        return lookup.get(vlg);
    }
}
