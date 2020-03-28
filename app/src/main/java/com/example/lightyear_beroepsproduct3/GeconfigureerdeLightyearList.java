package com.example.lightyear_beroepsproduct3;

import java.util.ArrayList;

public class GeconfigureerdeLightyearList {
    private final ArrayList<GeconfigureerdeLightyear> geconfigureerdeLightyearList;

    public GeconfigureerdeLightyearList() {
        geconfigureerdeLightyearList = new ArrayList<GeconfigureerdeLightyear>();
    }

    public void addGeconfigureerdeLightyear(GeconfigureerdeLightyear cl) {
        geconfigureerdeLightyearList.add(cl);
    }

    public ArrayList returnGeconfigureerdeLightyearList() {
        return geconfigureerdeLightyearList;
    }
}
