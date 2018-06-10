package com.company.vehicles;

import com.company.components.engine.Engine;
import com.company.components.model.VehicleModel;
import com.company.components.transmission.Transmission;

/**
 * Instances of this class represent real-world cars
 */
public class Car extends Vehicle {
    private static final String CAR_TYPE = "car";
    private Transmission transmission;

    /**
     * @param engine       the engine of the vehicle
     * @param model        the model of the vehicle
     * @param transmission the transmission of the vehicle
     * @param vin          the VIN code of the vehicle
     */
    public Car(Engine engine, VehicleModel model, Transmission transmission, String vin) {
        super(engine, model, vin);
        this.transmission = transmission;
        validateModel(model);
    }

    void validateModel(VehicleModel model) {
        if (!model.getVehicleType().equals("car")) {
            throw new IllegalArgumentException(model.getModel() + " is not a valid car model");
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " " + transmission.getInfo();
    }
}
