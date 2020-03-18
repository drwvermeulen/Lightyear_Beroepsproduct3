package com.example.lightyear_beroepsproduct3;

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

    @Override
    public String toString() {
        return stringValue;
    }
}
