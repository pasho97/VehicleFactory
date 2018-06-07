package com.company.components.engine;

import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.parsers.EngineSpecificationsParser;

import java.util.List;

/**
 * Builder pattern implementation designed for the EngineFactory class
 */
public class EngineFactoryBuilder {
    private List<EngineSpecifications> engineSpecificationsList;
    private EmissionStandardFactory emissionStandardFactory;
    private List<EngineSpecificationsParser> parsers;

    /**
     * @param engineSpecificationsList List of engine specifications the built Engine factory should support
     */
    public EngineFactoryBuilder setEngineSpecificationsList(List<EngineSpecifications> engineSpecificationsList) {
        this.engineSpecificationsList = engineSpecificationsList;
        return this;
    }

    /**
     * @param emissionStandardFactory The emission standard factory needed for setting the right emission standard by
     *                                the factory
     */
    public EngineFactoryBuilder setEmissionStandardFactory(EmissionStandardFactory emissionStandardFactory) {
        this.emissionStandardFactory = emissionStandardFactory;
        return this;
    }

    /**
     * @param parsers The engine specifications objects parsers
     */
    public EngineFactoryBuilder setParsers(List<EngineSpecificationsParser> parsers) {
        this.parsers = parsers;
        return this;
    }

    public EngineFactory createEngineFactory() {
        return new EngineFactory(engineSpecificationsList, emissionStandardFactory, parsers);
    }
}