package com.company.components.engine.turbo;

import org.springframework.stereotype.Component;

/**
 * Immutable singleton pattern Turbo implementation that increases Kilowatt power by 30%
 */
@Component
public class BasicTurbo implements Turbo {

    private static final String id = "T";
    private static final double PERCENTAGE_INCREASE = 0.30;
    private static Turbo instance;

    private BasicTurbo() {
    }

    public static Turbo getInstance() {
        if (instance == null) {
            instance = new BasicTurbo();
        }
        return instance;
    }

    @Override
    public int getPowerIncreaseIfMounted(int kwPower) {
        return (int) (kwPower * PERCENTAGE_INCREASE);
    }

    @Override
    public int getPowerDecreaseIfDismounted(int kwPower) {
        return (int) (kwPower * PERCENTAGE_INCREASE / (PERCENTAGE_INCREASE + 1));
    }

    @Override
    public String getTypeId() {
        return id;
    }


}
