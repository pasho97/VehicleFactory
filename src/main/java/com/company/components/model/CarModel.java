package com.company.components.model;

/**
 * Instances of this class represent car models , car models have name of model and car body type
 */
public class CarModel implements VehicleModel {
    private final String model;
    private final BodyType type;

    /**
     * @param model String value representing the name of the model
     * @param type  BodyType enum representing the type of the car body
     */
    public CarModel(String model, BodyType type) {
        this.model = model;
        this.type = type;
    }


    @Override
    public String getModel() {
        return model + type.toString();
    }

    @Override
    public String getVehicleType() {
        return "car";
    }


}
