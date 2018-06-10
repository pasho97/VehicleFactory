package com.company.components.engine;

import com.company.components.engine.turbo.Turbo;

public abstract class InternalCombustionEngineSpecifications extends EngineSpecifications {

    private int displacement;
    private Turbo turbo;

    InternalCombustionEngineSpecifications(int displacement, double kw, Turbo turbo) {
        super(kw);
        this.turbo = turbo;
        setDisplacement(displacement);
    }

    /**
     * @return True if the engine specification has turbo , false otherwise
     */
    private boolean hasTurbo() {
        return turbo != null;
    }

    @Override
    public int getDisplacement() {
        return displacement;
    }

    void setDisplacement(int engineDisplacement) {
        this.displacement = engineDisplacement;
    }

    public void mountTurbo(Turbo newTurbo) {
        if (newTurbo != null) {
            if (!hasTurbo()) {
                this.turbo = newTurbo;
                kwPower += newTurbo.getPowerIncreaseIfMounted(kwPower);
            } else {
                kwPower -= this.turbo.getPowerDecreaseIfDismounted(kwPower);
                this.turbo = newTurbo;
                kwPower += newTurbo.getPowerIncreaseIfMounted(kwPower);
            }
        }
    }

    public String getTurboId() {
        if (turbo == null) {
            return null;
        }
        return turbo.getTypeId();
    }

}
