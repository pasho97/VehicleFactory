package com.company.command;

import com.company.storages.VehiclePersistentStorage;
import org.springframework.stereotype.Component;

/**
 * Command implementation used for printing all the vehicles by given euro standard
 */
@Component
public class FindCommand implements Command {
    private VehiclePersistentStorage storage;

    /**
     * @param storage storage used to store vehicles
     */
    public FindCommand(VehiclePersistentStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getCommandName() {
        return "find";
    }

    /**
     * @param emissionStandard The emission standard to look for
     * @return all the vehicles in persistent storage that have this standard
     */
    @Override
    public String interpret(String emissionStandard) {
        return storage.getByEmissionStandard(emissionStandard);
    }
}
