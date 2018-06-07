package com.company.components.engine.parsers;

import com.company.components.engine.EngineSpecifications;

import java.util.List;

public interface EngineSpecificationsParser {
    /**
     * @param properties The string containing the engine information
     * @return True if the parser can be used to parse this information
     */
    boolean canParse(String properties);

    /**
     * @param properties                The properties to be parsed
     * @param engineSpecificationsList  List with engine specifications
     * @return  The result engine specifications if parse was successful , null if parser cannot parse these properties
     * @throws EngineParseException  if no engine specifications matching the given properties were found
     */
    EngineSpecifications parse(String properties, List<EngineSpecifications> engineSpecificationsList);
}
