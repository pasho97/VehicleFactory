package com.company.components.model;

/**
 *  Instances of this class represent suv models , suv models have name of model only
 */
public class SuvModel implements VehicleModel {
    private String name;

    /**
     * @param modelName The name of the suv model
     */
    public SuvModel(String modelName) {
        name = modelName;
    }

    @Override
    public String getModel() {
        return name;
    }

    @Override
    public String getVehicleType() {
        return "suv";
    }
}
