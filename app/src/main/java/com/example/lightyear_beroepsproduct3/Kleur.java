package com.example.lightyear_beroepsproduct3;

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

    @Override
    public String toString() {
        return stringValue;
    }
}
