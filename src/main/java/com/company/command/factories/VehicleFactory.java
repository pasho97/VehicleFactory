package com.company.command.factories;

import com.company.vehicles.Vehicle;

public interface VehicleFactory {
    Vehicle create(String properties, String vin);

    String getType();
}
