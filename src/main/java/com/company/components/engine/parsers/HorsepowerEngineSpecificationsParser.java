package com.company.components.engine.parsers;

import com.company.components.engine.EngineSpecifications;

public class HorsepowerEngineSpecificationsParser extends EngineSpecificationsParser {
    private final int allowedDifference;

    public HorsepowerEngineSpecificationsParser(String identificator, int allowedDifference) {
        super(identificator);
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
