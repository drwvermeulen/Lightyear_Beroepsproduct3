package com.example.lightyear_beroepsproduct3;

import java.util.HashMap;
import java.util.Map;

public enum Lak {
    Unilak("Uni lak", 0),
    Metalliclak("Metallic lak", 1),
    Mattelak("Matte lak", 2);

    private String stringValue;
    private int intValue;
    private Lak(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, Lak> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Lak lk : Lak.values())
        {
            lookup.put(lk.toString(), lk);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Lak getLak(String lk)
    {
        return lookup.get(lk);
    }
}
