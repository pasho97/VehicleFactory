package com.company.command;

import com.company.VehiclePersistentStorage;
import org.springframework.stereotype.Component;

/**
 * Command implementation used for disassembling vehicles
 */
@Component
public class DisassembleCommand implements Command {
    private VehiclePersistentStorage storage;

    /**
     * @param storage storage used to store vehicles
     */
    DisassembleCommand(VehiclePersistentStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getCommandName() {
        return "disassemble";
    }

    @Override
    public String interpret(String stringToInterpret) {
        String result = storage.disassemble(stringToInterpret);
        if (result == null) {
            throw new IllegalArgumentException("Vehicle with this vin is already disassembled or does not exist");
        }

        return result;
    }
}
