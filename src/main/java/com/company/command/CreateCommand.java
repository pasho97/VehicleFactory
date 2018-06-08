package com.company.command;

import com.company.VehiclePersistentStorage;
import com.company.command.factories.VehicleFactory;
import com.company.components.VinGenerator;
import com.company.vehicles.Vehicle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Command implementation used for creating Vehicles
 */
@Component
public class CreateCommand implements Command {
    private final VehiclePersistentStorage storage;
    private final VinGenerator generator;
    private Map<String, VehicleFactory> creatorByTypeMap;


    /**
     * @param creatorList list of factories this command supports
     * @param storage storage used to store vehicles
     * @param generator vin generator
     */
    public CreateCommand(List<VehicleFactory> creatorList, VehiclePersistentStorage storage, VinGenerator generator) {

        this.storage = storage;
        this.generator = generator;
        creatorByTypeMap = new HashMap<>();
        creatorList.forEach(x -> creatorByTypeMap.put(x.getType(), x));
    }

    @Override
    public String getCommandName() {
        return "create";
    }

    @Override
    public String interpret(String stringToInterpret) {
        String[] arguments = stringToInterpret.split(" ");
        if (!creatorByTypeMap.containsKey(arguments[0])) {
            throw new UnsupportedOperationException("unsupported vehicle type");
        }
        String vin = generator.generate();
        while (!storage.uniqueVin(vin)) {
            vin = generator.generate();

        }
        Vehicle newVehicle = creatorByTypeMap.get(arguments[0])
                .create(stringToInterpret.replace(arguments[0] + " ", ""), vin);


        storage.store(newVehicle);

        return newVehicle.getInfo();
    }
}
