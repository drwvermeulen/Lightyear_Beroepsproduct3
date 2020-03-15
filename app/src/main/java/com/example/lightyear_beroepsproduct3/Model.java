package com.example.lightyear_beroepsproduct3;

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
}
