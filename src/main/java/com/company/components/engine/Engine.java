package com.company.components.engine;

import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.turbo.Turbo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objects of this class represent engines found in vehicles
 */
public class Engine {
    private static final Logger LOGGER = Logger.getLogger(Engine.class.getName());
    private EngineSpecifications engineSpecifications;
    private EmissionStandard standard;

    /**
     * @param specifications - Specifications of the required engine - fuel type , turbo , kilowatt power etc
     * @param standard       - The emission standard the required engine complies to
     */
    Engine(EngineSpecifications specifications, EmissionStandard standard) {
        this.engineSpecifications = specifications;
        this.standard = standard;
    }

    /**
     * @param turbo - mounts the given turbo to the engine
     * @throws IllegalArgumentException if the engine we try to mount the turbo on cannot have turbo
     */
    public void mountTurbo(Turbo turbo) {
        try {
            engineSpecifications = (EngineSpecifications) engineSpecifications.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.log(Level.SEVERE, "clone failed ");
        }
        engineSpecifications.mountTurbo(turbo);
    }

    /**
     * @return String representing the engine type id
     */
    public String getTypeId() {
        return engineSpecifications.getTypeId();
    }

    /**
     * @return The turbo id of the Engine , null if the engine does not have a turbo
     */
    public String getTurboId() {
        return engineSpecifications.getTurboId();
    }

    /**
     * @return The kilowatt power of the engine
     */
    public int getKwPower() {

        return engineSpecifications.getKwPower();
    }

    /**
     * @return String containing the engine data in a way that it can be parsed later to an engine object having the same
     * properties
     */
    public String getInfo() {
        return engineSpecifications.getTurboId() != null ?
                String.join("-", engineSpecifications.getTypeId(), getHorsepowerString(),
                        engineSpecifications.getTurboId(), getEmissionStandard()) :
                String.join("-", engineSpecifications.getTypeId(), getHorsepowerString(), getEmissionStandard());
    }

    /**
     * @return The name of the emission standard of the engine
     */
    public String getEmissionStandard() {
        return standard.getName();
    }

    private String getHorsepowerString() {
        return engineSpecifications.getHorsepower() + "hp";
    }
}
