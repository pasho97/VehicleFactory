package com.company.engine.turbo;

public interface Turbo {
    int getPowerIncreaseIfMounted(int kwPower);
    int getPowerDecreaseIfUnmounted(int kwPower);
    String getTypeId();
}
