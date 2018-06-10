package com.company.vehicles.factories;

import com.company.vehicles.Vehicle;

/**
 * Factory pattern interface for Vehicles
 */
public interface VehicleFactory {
    /**
     * @param properties vehicle properties
     * @param vin        vin for the vehicle
     * @return Vehicle object if there are no errors
     * @throws IllegalArgumentException if any error occurs with the properties
     */
    Vehicle create(String properties, String vin);

    /**
     * @return String representing the vehicle types this factory creates
     */
    String getType();
}
