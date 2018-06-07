package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

import java.util.logging.Logger;

/**
 * Abstract class representing engine specifications such as kilowatt power , engine type , turbo and displacement
 */
public abstract class EngineSpecifications implements Cloneable {
    private final static double KILOWATT_TO_HORSEPOWER = 1.34102209;
    private final static Logger LOGGER = Logger.getLogger(EngineSpecifications.class.getName());
    double kwPower;

    EngineSpecifications(double kw) {
        setKwPower(kw);
    }

    public abstract String getTurboId();


    /**
     * @return The horsepower of the engine
     */
    public int getHorsepower() {
        return (int) (kwPower * KILOWATT_TO_HORSEPOWER);
    }


    /**
     * @return The typeId specification of the engine
     */
    abstract String getTypeId();

    /**
     * @return The displacement specification of the engine , 0 if the engine has no displacement(e.g. Electric engine)
     */
    public abstract int getDisplacement();

    /**
     * @return The kilowatt power specification of the engine
     */
    public double getKwPower() {
        return kwPower;
    }

    private void setKwPower(double kw) {
        this.kwPower = kw;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }

    public abstract void mountTurbo(Turbo turbo);

}
