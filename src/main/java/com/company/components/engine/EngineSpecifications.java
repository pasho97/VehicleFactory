package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

import java.util.logging.Logger;

/**
 * Abstract class representing engine specifications such as kilowatt power , engine type , turbo and displacement
 */
public abstract class EngineSpecifications implements Cloneable{
    private final static double KILOWATT_TO_HORSEPOWER = 1.34102209;
    private final static Logger LOGGER = Logger.getLogger(EngineSpecifications.class.getName());
    private int displacement;
    private int kwPower;
    private Turbo turbo;

    EngineSpecifications(int displacement, int kw, Turbo turbo) {
        setDisplacement(displacement);
        setKwPower(kw);
        this.turbo = turbo;
    }

    /**
     * @return The horsepower of the engine
     */
    public int getHorsepower() {
        return (int) (kwPower * KILOWATT_TO_HORSEPOWER);
    }

    /**
     * @return True if the engine specification has turbo , false otherwise
     */
    public boolean hasTurbo() {
        return turbo != null;
    }

    public void mountTurbo(Turbo turbo) {
        if (turbo != null) {
            if (!this.getTurboId().equals(turbo.getTypeId())) {
                this.turbo = turbo;
            }
            kwPower += turbo.getPowerIncreaseIfMounted(kwPower);
        }
    }

    public String getTurboId() {
        if (turbo == null) {
            return "";
        }
        return turbo.getTypeId();
    }

    /**
     * @return The typeId specification of the engine
     */
    abstract String getTypeId();

    /**
     * @return The displacement specification of the engine , 0 if the engine has no displacement(e.g. Electric engine)
     */
    public int getDisplacement() {
        return displacement;
    }

    void setDisplacement(int engineDisplacement) {
        this.displacement = engineDisplacement;
    }

    /**
     * @return The kilowatt power specification of the engine
     */
    public int getKwPower() {
        return kwPower;
    }

    private void setKwPower(int kw) {
        this.kwPower = kw;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

            return super.clone();

    }
}
