package com.example.lightyear_beroepsproduct3;

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
}
