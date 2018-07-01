package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

/**
 * Class representing petrol fueled internal combustion engines
 */
public class PetrolEngineSpecifications extends InternalCombustionEngineSpecifications {
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
