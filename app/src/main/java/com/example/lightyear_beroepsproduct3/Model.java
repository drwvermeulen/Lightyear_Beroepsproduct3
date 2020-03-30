package com.example.lightyear_beroepsproduct3;

import java.util.HashMap;
import java.util.Map;

public enum Model {
    LightyearOne("Lightyear - One", 0),
    LightyearPioneer("Lightyear - Pioneer Edition", 1);

    private String stringValue;
    private int intValue;
    private Model(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    public Double getPrijs() {
        Double prijs = 0.00;
        switch (intValue) {
            case 0:
                prijs = 149000.00;
                break;
            case 1:
                prijs = 199000.00;
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
    private static final Map<String, Model> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Model mdl : Model.values())
        {
            lookup.put(mdl.toString(), mdl);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Model getModel(String mdl)
    {
        return lookup.get(mdl);
    }
}
