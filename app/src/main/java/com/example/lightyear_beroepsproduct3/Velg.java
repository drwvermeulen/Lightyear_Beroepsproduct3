package com.example.lightyear_beroepsproduct3;

public enum Velg {
    Velgen16("16 Velgen", 0),
    Velgen17("17 Velgen", 1),
    Velgen18("18 Velgen", 2);

    private String stringValue;
    private int intValue;
    private Velg(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
