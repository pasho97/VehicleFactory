package com.company.car.engine.turbo;

/**
 * An object that represents turbos found in automobile engines
 */
public interface Turbo {

    /**
     * Constructor
     *
     * @param kwPower The current kilowatts power of the engine
     * @return The power increase an engine with the given kilowatts power and no turbo would get
     * if added this type of turbo
     */
    int getPowerIncreaseIfMounted(int kwPower);

    /**
     * @param kwPower The current kilowatts power of the engine
     * @return The power decrease dismounting this turbo from an engine with the given kilowatts would lead to
     */
    int getPowerDecreaseIfDismounted(int kwPower);


    /**
     * @return The id of the turbo's type
     */
    String getTypeId();
}
