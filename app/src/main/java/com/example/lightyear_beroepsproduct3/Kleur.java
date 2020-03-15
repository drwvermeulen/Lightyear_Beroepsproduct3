package com.example.lightyear_beroepsproduct3;

public enum Kleur {
    Zwart("Zwart", 0),
    Wit("Wit", 1),
    Rood("Rood", 2),
    Blauw("Blauw", 3);

    private String stringValue;
    private int intValue;
    private Kleur(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
