package com.company.components.engine.parsers;

import com.company.components.engine.EngineSpecifications;

/**
 * Engine specifications parser for horsepower type of input information
 */
public class HorsepowerEngineSpecificationsParser extends EngineSpecificationsPowerValueParser {
    private final int allowedDifference;

    public HorsepowerEngineSpecificationsParser(String identifier, int allowedDifference) {
        super(identifier);
        this.allowedDifference = allowedDifference;
    }


    private int parseHorsepower(String enginePowerProperty) {
        return Integer.parseInt(enginePowerProperty.replaceFirst(getEnginePowerValueTypeIdentifier() + ".*", ""));
    }

    @Override
    boolean haveRequestedPower(EngineSpecifications engineSpecifications, String enginePowerProperty) {
        int horsepower = parseHorsepower(enginePowerProperty);
        return horsepower - allowedDifference <= engineSpecifications.getHorsepower() &&
                engineSpecifications.getHorsepower() <= horsepower + allowedDifference;
    }
}
