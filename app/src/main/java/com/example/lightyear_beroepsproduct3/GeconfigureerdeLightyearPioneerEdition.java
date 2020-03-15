package com.example.lightyear_beroepsproduct3;

import java.io.Serializable;

public class GeconfigureerdeLightyearPioneerEdition extends GeconfigureerdeLightyear implements Serializable {
    private Integer pnrdtn;

    public GeconfigureerdeLightyearPioneerEdition(Model model) {
        super(model);
    }

    public GeconfigureerdeLightyearPioneerEdition(Model mdl, Integer pnrdtn) {
        super(mdl);
        this.pnrdtn = pnrdtn;
    }

    public Integer getPnrdtn() {
        return pnrdtn;
    }

    public void setPnrdtn(Integer pnrdtn) {
        this.pnrdtn = pnrdtn;
    }
}
