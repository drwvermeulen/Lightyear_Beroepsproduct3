package com.example.lightyear_beroepsproduct3;

import java.util.HashMap;
import java.util.Map;

public enum Model {
    LightyearOne("Lightyear One", 0),
    LightyearOnePioneer("Lightyear One - Pioneer Edition", 1);

    private String stringValue;
    private int intValue;
    private Model(String toString, int value) {
        stringValue = toString;
        intValue = value;
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
