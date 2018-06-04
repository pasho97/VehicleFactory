package com.company.car.engine.emission.standards;

/**
 * Objects of this class represent the euro emission standards
 */
public class EuroEmissionStandard implements EmissionStandard {
    private final int version;

    /**
     * @param version - integer representing the euro standard version (e.g. 3 for euro3 )
     */
    public EuroEmissionStandard(int version) {
        this.version = version;
    }


    @Override
    public String getName() {
        return "euro" + version;
    }
}
