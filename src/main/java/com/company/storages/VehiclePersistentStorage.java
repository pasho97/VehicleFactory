package com.company.storages;

import com.company.vehicles.Vehicle;

/**
 * Interface for persistent storage logic for storing vehicles
 */
public interface VehiclePersistentStorage {
    /**
     * @param vehicle The vehicle to be stored
     */
    void store(Vehicle vehicle);

    /**
     * @param vin The VIN of the vehicle whose information is requested
     * @return information about the vehicle with that vin
     */
    String getInfoByVin(String vin);

    /**
     * @return information about all vehicles , both disassembled and not disassembled
     */
    String getAllInfo();

    /**
     * @param vin VIN of the vehicle to be disassembled
     * @return info about the disassembled vehicle
     */
    String disassemble(String vin);

    /**
     * @param standard String containing name of emission standard
     * @return info about all the vehicles complying to this standard
     */
    String getByEmissionStandard(String standard);

    /**
     * @param vin    VIN of the vehicle to be updated
     * @param fields new fields to be set in the storage
     */
    void updateByVin(String vin, String fields);

    /**
     * @param vin     VIN of the vehicle
     * @param colName Name of the columd
     * @return The value in the column with the given name for the vehicle with the given vin
     */
    String getColByVin(String vin, String colName);

    /**
     * @param vin VIN to be checked
     * @return True if there is no vehicle with that vin , otherwise False
     */
    boolean uniqueVin(String vin);
}
