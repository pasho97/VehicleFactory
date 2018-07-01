package com.company.components.engine;

import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.parsers.EngineSpecificationsParser;
import com.company.components.engine.parsers.UnsupportedEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Class implementing Factory design pattern for Engine objects
 */
@Component
public class EngineFactory {
    private final EmissionStandardFactory emissionStandardFactory;
    private final List<EngineSpecificationsParser> parsers;
    private Map<String, List<EngineSpecifications>> enginesByType;

    /**
     * @param engineSpecificationsList List of engine specifications this factory will support
     * @param emissionStandardFactory  emission standard factory needed to set the right emission standard for the
     *                                 required engine object
     * @param parsers                  list of parsers having the different string input parser types the engine can be build from
     */
    @Autowired
    EngineFactory(List<EngineSpecifications> engineSpecificationsList, EmissionStandardFactory emissionStandardFactory,
                         List<EngineSpecificationsParser> parsers) {
        this.emissionStandardFactory = emissionStandardFactory;
        this.parsers = parsers;
        this.enginesByType = new HashMap<>();
        engineSpecificationsList.forEach(x -> {
            if (!enginesByType.containsKey(x.getTypeId())) {
                enginesByType.put(x.getTypeId(), new LinkedList<>());
            }
            enginesByType.get(x.getTypeId()).add(x);
        });
        enginesByType.forEach((x, y) -> y.sort(Comparator.comparingDouble(EngineSpecifications::getKwPower)));

    }

    /**
     * @param properties The properties the required engine must meet
     * @return Engine object meeting the properties required
     * @throws UnsupportedEngineException if such an Engine is unsupported
     */
    public Engine getEngine(String properties) {
        String[] splitProperties = properties.split("-");
        if (!enginesByType.containsKey(splitProperties[0])) {
            throw new IllegalArgumentException("Illegal engine type");
        }
        List<EngineSpecifications> engineSpecificationsList = enginesByType.get(splitProperties[0]);

        for (EngineSpecificationsParser parser : parsers
                ) {
            if (parser.canParse(properties)) {
                EngineSpecifications result = parser.parse(properties, engineSpecificationsList);
                EmissionStandard emissionStandard = null;

                if (result.getTypeId().equals("E")) {
                    emissionStandard = emissionStandardFactory.getHighestStandard();
                }
                if (splitProperties.length == 3) {
                    if (emissionStandard != null && !emissionStandard.getName().equals(
                            emissionStandardFactory.getStandardInstance(splitProperties[2]).getName())) {
                        throw new IllegalArgumentException("Electrical engines should " +
                                "have highest possible emission standard");
                    }

                    emissionStandard = emissionStandardFactory.getStandardInstance(splitProperties[2]);
                } else if (!result.getTypeId().equals("E")) {
                    emissionStandard = emissionStandardFactory.getStandardInstance("");
                }

                return new Engine(result, emissionStandard);
            }
        }

        throw new EngineParseException();
    }

}
