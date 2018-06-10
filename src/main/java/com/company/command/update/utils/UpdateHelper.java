package com.company.command.update.utils;

/**
 * Interface class whose implementations should contain logic for parsing different string arguments used in updating
 * vehicles
 */
public interface UpdateHelper {
    /**
     * @param currentArgs String representing the current arguments defining the vehicle
     * @param newArgs     String representing the args defining the requested vehicle updates
     * @param delimiter   the string used to split the arguments
     * @return String containing all the needed information about the already updated component
     * @throws UnsupportedOperationException if an error occurs
     */
    String getUpdatedComponentString(String[] currentArgs, String newArgs, String delimiter);

    /**
     * @return String representing the component the current class can be used to update
     */
    String getType();
}
