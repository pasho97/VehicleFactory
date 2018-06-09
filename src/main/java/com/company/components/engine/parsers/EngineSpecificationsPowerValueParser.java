package com.company.components.engine.parsers;

import com.company.components.engine.EngineParseException;
import com.company.components.engine.EngineSpecifications;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classes extending this abstract class implement engine specifications parser
 */
public abstract class EngineSpecificationsPowerValueParser implements EngineSpecificationsParser {
    private final String enginePowerValueTypeIdentifier;
    private final Pattern pattern;

    /**
     * @param enginePowerValueTypeIdentifier The string the power value will be identified with ( e.g. one possible id for
     *                                       horsepower is hp )
     */
    public EngineSpecificationsPowerValueParser(String enginePowerValueTypeIdentifier) {
        this.enginePowerValueTypeIdentifier = enginePowerValueTypeIdentifier;
        pattern = Pattern.compile("([1-9][0-9]*" + enginePowerValueTypeIdentifier + ")([?T]?)");
    }

    @Override
    public boolean canParse(String properties) {
        return pattern.matcher(properties).find();
    }


    private boolean hasSameTurboIfRequestedExplicitly(EngineSpecifications engineSpecifications, String turboId) {
        if (turboId == null) {
            return true;
        }
        return turboId.equals("") || turboId.equals(engineSpecifications.getTurboId());
    }

    /**
     * @param specifications       The engine specifications we want to check if they comply to the requested specifications
     * @param enginePowerProperty  The requested specifications as string
     * @return True if specifications have the same power requested by enginePowerProperty
     */
    abstract boolean haveRequestedPower(EngineSpecifications specifications, String enginePowerProperty);

    private boolean isRequestedEngine(EngineSpecifications engineSpecifications, String enginePowerProperty, String turboProperty) {
        return haveRequestedPower(engineSpecifications, enginePowerProperty) &&
                hasSameTurboIfRequestedExplicitly(engineSpecifications, turboProperty);
    }

    @Override
    public EngineSpecifications parse(String properties, List<EngineSpecifications> engineSpecificationsList) {
        Matcher matcher = pattern.matcher(properties);
        if (!matcher.find()) {
            return null;
        }

        String enginePowerProperty = matcher.group(1);
        String turboIdProperty = matcher.group(2);
        for (EngineSpecifications engineSpecifications : engineSpecificationsList
                ) {
            if (isRequestedEngine(engineSpecifications, enginePowerProperty, turboIdProperty)) {
                return engineSpecifications;
            }
        }

        throw new UnsupportedEngineException();
    }

    /**
     * @return String representing the engine power value type identifier
     */
    String getEnginePowerValueTypeIdentifier() {
        return enginePowerValueTypeIdentifier;
    }
}
