package com.company.vehicles;

import com.company.components.engine.Engine;
import com.company.components.model.VehicleModel;

/**
 * Instances of this class represent real-world vehicles with electric engines
 */
public class ElectricVehicle extends Vehicle {
    public ElectricVehicle(Engine engine, VehicleModel model, String vin) {
        super(engine, model, vin);
    }


}
