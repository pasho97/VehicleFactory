package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

public class PetrolEngineSpecifications extends EngineSpecifications {
    private static final String TYPE_ID = "B";

    public PetrolEngineSpecifications(int displacement, int kw, Turbo turbo) {
        super(displacement, kw, turbo);
    }

    public PetrolEngineSpecifications(int displacement, int kw) {
        super(displacement, kw, null);
    }

    @Override
    String getTypeId() {
        return TYPE_ID;
    }


}
