package com.company.vehicles;

import com.company.components.engine.Engine;
import com.company.components.model.VehicleModel;
import com.company.components.transmission.Transmission;

/**
 * Instances of this class represent real-world SUV vehicles
 */
public class SuvVehicle extends Car {
    public SuvVehicle(Engine engine, VehicleModel model, Transmission transmission, String vin) {
        super(engine, model, transmission, vin);
        validateModel(model);
    }

    @Override
    public void validateModel(VehicleModel model) {
        if (!model.getVehicleType().equals("suv")) {
            throw new IllegalArgumentException(model.getModel() + " is not a valid suv model");
        }
    }

}
