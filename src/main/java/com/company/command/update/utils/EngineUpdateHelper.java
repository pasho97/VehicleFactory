package com.company.command.update.utils;

import com.company.components.engine.Engine;
import com.company.components.engine.EngineFactory;
import com.company.components.engine.turbo.BasicTurbo;
import org.springframework.stereotype.Component;

@Component
public class EngineUpdateHelper implements UpdateHelper {
    private final EngineFactory engineFactory;

    /**
     * @param engineFactory factory used for validating and eventually returning the requested engine component
     */
    public EngineUpdateHelper(EngineFactory engineFactory) {
        this.engineFactory = engineFactory;
    }

    @Override
    public String getUpdatedComponentString(String[] currentEngineArgs, String inputArgs, String delimiter) {
        String[] engineArgs = inputArgs.split(delimiter, -1);
        if (!(engineArgs.length == 3)) {
            throw new IllegalArgumentException("Illegal engine update arguments");
        }
        if (!engineArgs[2].equals("")) {
            currentEngineArgs[2] = engineArgs[2];
        }
        Engine engine = engineFactory.getEngine(String.join("-", currentEngineArgs));
        if (engineArgs[1].equals("*T")) {
            if (engine.getTurboId() == null) {
                engine.mountTurbo(BasicTurbo.getInstance());
            }
        }

        return "engine='" + engine.getInfo() + "'";
    }

    @Override
    public String getType() {
        return "engine";
    }
}
