package com.company.vehicles.factories;

import com.company.components.engine.Engine;
import com.company.components.engine.EngineFactory;
import com.company.components.model.ModelFactory;
import com.company.components.model.VehicleModel;
import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionFactory;
import com.company.vehicles.SuvVehicle;
import com.company.vehicles.Vehicle;
import org.springframework.stereotype.Component;

/**
 * Factory pattern implementation for SuvVehicle instances
 */
@Component
public class SuvFactory extends CarFactory {
    public SuvFactory(EngineFactory engineFactory, ModelFactory modelFactory, TransmissionFactory transmissionFactory) {
        super(engineFactory, modelFactory, transmissionFactory);
    }

    @Override
    public String getType() {
        return "suv";
    }

    @Override
    Vehicle createInstance(Engine engine, VehicleModel model, Transmission transmission, String vin) {
        return new SuvVehicle(engine, model, transmission, vin);
    }

    @Override
    VehicleModel getModel(String modelInfo) {
        VehicleModel model = modelFactory.getModel(modelInfo);

        if (!model.getVehicleType().equals("suv")) {
            throw new IllegalArgumentException("given model isn't a valid suv model");
        }

        return model;
    }
}
