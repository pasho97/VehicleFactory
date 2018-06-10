package com.company.command;

import com.company.storages.VehiclePersistentStorage;
import org.springframework.stereotype.Component;

@Component
public class PrintCommand implements Command {
    private VehiclePersistentStorage storage;

    /**
     * @param storage the storage where the vehicles are stored
     */
    PrintCommand(VehiclePersistentStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getCommandName() {
        return "print";
    }

    /**
     * @param stringToInterpret vin of the vehicle needed to be printed or "all" - for all vehicles
     * @return The
     */
    @Override
    public String interpret(String stringToInterpret) {
        if (stringToInterpret.equals("all")) {
            return storage.getAllInfo();
        }

        String result = storage.getInfoByVin(stringToInterpret);

        if (result == null) {
            throw new IllegalArgumentException("vehicle with the required vin does not exist");
        }
        return result;
    }
}
