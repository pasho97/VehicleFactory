package com.company.components.engine.parsers;

import com.company.components.engine.EngineSpecifications;


public class DisplacementEngineSpecificationsParser extends EngineSpecificationsPowerValueParser {

    public DisplacementEngineSpecificationsParser(String id) {
        super(id);
    }

    @Override
    boolean haveRequestedPower(EngineSpecifications specifications, String enginePowerProperty) {
        return specifications.getDisplacement() == parseDisplacement(enginePowerProperty);
    }


    private int parseDisplacement(String enginePowerProperty) {
        return Integer.parseInt(enginePowerProperty.replaceFirst(getEnginePowerValueTypeIdentifier() + ".*", "000"));
    }


}
