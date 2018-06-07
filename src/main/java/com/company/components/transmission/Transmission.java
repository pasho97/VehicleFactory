package com.company.components.transmission;

/**
 * Instances of this class represent vehicle transmissions
 */
public class Transmission {
    private int gears;
    private TransmissionType type;

    /**
     * @param gears gears count of transmission
     * @param type type of transmission
     */
    Transmission(int gears, TransmissionType type) {
        this.type = type;
        this.gears = gears;
    }

    /**
     * @param first first transmission
     * @param second second transmission
     * @return 1 if first transmission is higher class than second , 0 if they are equal or -1 if second is higher class
     */
    static int compare(Transmission first, Transmission second) {
        if (first.type.compareTo(second.type) == 0) {
            return Integer.compare(first.gears, second.gears);
        }

        return first.type.compareTo(second.type);
    }

    /**
     * @return String representing the transmission object
     */
    public String getInfo() {
        return type.getId() + "-" + String.valueOf(gears);
    }

    /**
     * @return String representing the transmission type
     */
    public String getType(){
        return type.getId();
    }
}
