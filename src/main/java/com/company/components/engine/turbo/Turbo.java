package com.company.components.engine.turbo;

/**
 * An object that represents turbos found in automobile engines
 */
public interface Turbo {

    /**
     * @param kwPower The current kilowatts power of the engine
     * @return The power increase an engine with the given kilowatts power and no turbo would get
     * if added this type of turbo
     */
    double getPowerIncreaseIfMounted(double kwPower);

    /**
     * @param kwPower The current kilowatts power of the engine
     * @return The power decrease dismounting this turbo from an engine with the given kilowatts would lead to
     */
    double getPowerDecreaseIfDismounted(double kwPower);


    /**
     * @return The id of the turbo's type
     */
    String getTypeId();
}
