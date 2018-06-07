package com.company.vehicles;

import com.company.components.engine.Engine;
import com.company.components.model.VehicleModel;

public abstract class Vehicle {
    private Engine engine;
    private VehicleModel model;
    private String vin;

    /**
     * @param engine the engine of the vehicle
     * @param model the model of the vehicle
     * @param vin the vin code of the vehicle
     */
    public Vehicle(Engine engine, VehicleModel model, String vin) {
        this.engine = engine;
        this.model = model;
        this.vin = vin;
    }

    /**
     * @return String with the information about the vehicle
     */
    public String getInfo() {
        return vin + " " + model.getModel() + " " + engine.getInfo();
    }

    /**
     * @return String representing the vin code of the vehicle
     */
    public String getVin() {
        return vin;
    }

}
