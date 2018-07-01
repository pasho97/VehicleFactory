package com.company.components.engine.parsers;

import com.company.components.engine.EngineSpecifications;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Engine specifications parser used when only a type of engine is given
 */
@Component
public class EngineTypeEngineSpecificationsParser implements EngineSpecificationsParser {


    @Override
    public boolean canParse(String properties) {
        return properties.matches("^.$");
    }

    @Override
    public EngineSpecifications parse(String properties, List<EngineSpecifications> engineSpecificationsList) {
        return engineSpecificationsList.get(0);
    }
}
