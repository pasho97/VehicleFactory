package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

/**
 * This class represents electrical engine
 */
public class ElectricEngineSpecification extends EngineSpecifications {
    public ElectricEngineSpecification(int kw) {
        super(kw);
    }

    @Override
    public String getTurboId() {
        return null;
    }

    /**
     * @param turbo - The turbo we want to mount
     * @throws IllegalArgumentException - electrical engines cannot have turbo
     */
    @Override
    public void mountTurbo(Turbo turbo) {
        throw new UnsupportedOperationException("Turbo cannot be mounted in electrical engines");
    }

    @Override
    String getTypeId() {
        return "E";
    }

    @Override
    public int getDisplacement() {
        return 0;
    }
}
