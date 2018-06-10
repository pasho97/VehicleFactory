package com.company.command;


import com.company.command.update.utils.UpdateHelper;
import com.company.storages.VehiclePersistentStorage;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UpdateCommand implements Command {
    private final VehiclePersistentStorage storage;
    private final Map<String, UpdateHelper> updaterMap;

    /**
     * @param storage       storage used to store vehicles
     * @param updateHelpers list of {@link UpdateHelper} implementations having logic for parsing and updating the vehicle
     */
    public UpdateCommand(VehiclePersistentStorage storage, List<UpdateHelper> updateHelpers) {
        this.storage = storage;
        updaterMap = new HashMap<>();
        updateHelpers.forEach(x -> updaterMap.put(x.getType(), x));
    }

    @Override
    public String getCommandName() {
        return "update";
    }

    /**
     * @param stringToInterpret vin + the components to be updated , all joined by single space
     * @return information about the updated vehicle with the already updated components
     */
    @Override
    public String interpret(String stringToInterpret) {
        String vin = stringToInterpret.split(" ")[0];
        String args = stringToInterpret.split(" ", 2)[1];

        Map<String, String> argsByPropertyNameMap = new HashMap<>();
        Arrays.stream(args.split(" ")).forEach(x -> {
            String[] temp = x.split("=");
            if (temp.length != 2) {
                throw new IllegalArgumentException("Illegal update arguments format");
            }
            argsByPropertyNameMap.put(temp[0], temp[1]);
        });
        List<String> updateParams = new ArrayList<>();
        for (String key : argsByPropertyNameMap.keySet()
                ) {
            if (!updaterMap.containsKey(key)) {
                throw new UnsupportedOperationException("unsupported update operation");
            }
            updateParams.add(updaterMap.get(key).getUpdatedComponentString(storage.getColByVin(vin, key).split("-"),
                    argsByPropertyNameMap.get(key), "-"));
        }
        String update = String.join(", ", updateParams);
        storage.updateByVin(vin, update);

        return "updated: " + storage.getInfoByVin(vin);
    }
}
