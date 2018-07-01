package com.company.command;

import com.company.components.VinGenerator;
import com.company.storages.VehiclePersistentStorage;
import com.company.vehicles.Vehicle;
import com.company.vehicles.factories.VehicleFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command implementation used for creating Vehicles
 */
@Component
public class CreateCommand implements Command {
    private final VehiclePersistentStorage storage;
    private final VinGenerator generator;
    private Map<String, VehicleFactory> vehicleFactoryByType;


    /**
     * @param creatorList list of factories this command supports
     * @param storage     storage used to store vehicles
     * @param generator   vin generator
     */
    public CreateCommand(List<VehicleFactory> creatorList, VehiclePersistentStorage storage, VinGenerator generator) {

        this.storage = storage;
        this.generator = generator;
        vehicleFactoryByType = new HashMap<>();
        creatorList.forEach(x -> vehicleFactoryByType.put(x.getType(), x));
    }

    @Override
    public String getCommandName() {
        return "create";
    }

    @Override
    public String interpret(String stringToInterpret) {
        String[] arguments = stringToInterpret.split(" ");
        if (!vehicleFactoryByType.containsKey(arguments[0])) {
            throw new UnsupportedOperationException("unsupported vehicle type");
        }
        String vin = generator.generate();
        while (!storage.uniqueVin(vin)) {
            vin = generator.generate();

        }
        Vehicle newVehicle = vehicleFactoryByType.get(arguments[0])
                .create(stringToInterpret.replace(arguments[0] + " ", ""), vin);


        storage.store(newVehicle);

        return newVehicle.getInfo();
    }
}
