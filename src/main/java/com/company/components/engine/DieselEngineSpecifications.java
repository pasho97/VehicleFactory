package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

public class DieselEngineSpecifications extends InternalCombustionEngineSpecifications {
    private static final int DISPLACEMENT_LOWER_BOUND = 2000;
    private static final int DISPLACEMENT_UPPER_BOUND = 6000;
    private static final String TYPE_ID = "D";

    public DieselEngineSpecifications(int displacement, int kw, Turbo turbo) {
        super(displacement, kw, turbo);
    }

    public DieselEngineSpecifications(int displacement, int kw) {
        super(displacement, kw, null);
    }

    @Override
    void setDisplacement(int engineDisplacement) {
        if (DISPLACEMENT_LOWER_BOUND > engineDisplacement || engineDisplacement > DISPLACEMENT_UPPER_BOUND) {
            throw new IllegalArgumentException("Diesel engines must have displacement between"
                    + DISPLACEMENT_LOWER_BOUND
                    + " and "
                    + DISPLACEMENT_UPPER_BOUND);
        }
        super.setDisplacement(engineDisplacement);
    }

    @Override
    String getTypeId() {
        return TYPE_ID;
    }

}
