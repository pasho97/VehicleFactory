package com.company.command.factories;

import com.company.components.engine.Engine;
import com.company.components.engine.EngineFactory;
import com.company.components.model.ModelFactory;
import com.company.components.model.VehicleModel;
import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionFactory;
import com.company.vehicles.Car;
import com.company.vehicles.ElectricVehicle;
import com.company.vehicles.Vehicle;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CarFactory implements VehicleFactory {
    ModelFactory modelFactory;
    private EngineFactory engineFactory;
    private TransmissionFactory transmissionFactory;

    public CarFactory(EngineFactory engineFactory, ModelFactory modelFactory, TransmissionFactory transmissionFactory) {
        this.transmissionFactory = transmissionFactory;
        this.engineFactory = engineFactory;
        this.modelFactory = modelFactory;

    }

    VehicleModel getModel(String modelInfo) {
        VehicleModel model = modelFactory.getModel(modelInfo);

        if (!model.getVehicleType().equals("car")) {
            throw new IllegalArgumentException("given model isn't a valid car model");
        }

        return model;
    }

    @Override
    public Vehicle create(String properties, String vin) {
        String[] splitProperties = properties.split(" ");
        HashMap<String, String> propertiesMap = new HashMap<>();
        for (String property : splitProperties
                ) {
            String[] propertyTypeAndRequirements = property.split("=");
            if (propertyTypeAndRequirements.length != 2) {
                throw new IllegalArgumentException("Illegal arguments");
            }
            propertiesMap.put(propertyTypeAndRequirements[0], propertyTypeAndRequirements[1]);
        }
        if (!propertiesMap.containsKey("engine")) {
            throw new IllegalArgumentException("Cannot find engine information");
        }
        Engine engine = engineFactory.getEngine(propertiesMap.get("engine"));

        if (!propertiesMap.containsKey("model")) {
            throw new IllegalArgumentException("Cannot find model information");
        }

        VehicleModel model = getModel(propertiesMap.get("model"));

        if (engine.getTypeId().equals("E")) {
            if (propertiesMap.containsKey("transmission")) {
                throw new IllegalArgumentException("Electrical engines can't have transmissions");
            }

            return new ElectricVehicle(engine, model, vin);
        }
        Transmission transmission;

        if (!propertiesMap.containsKey("transmission")) {
            transmission = transmissionFactory.getTransmission(null);
        } else {
            transmission = transmissionFactory.getTransmission(propertiesMap.get("transmission"));
        }

        return new Car(engine, model, transmission, vin);
    }

    @Override
    public String getType() {
        return "car";
    }
}
