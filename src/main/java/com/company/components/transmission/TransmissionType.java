package com.company.components.transmission;

/**
 * Transmission types
 */
public enum TransmissionType {
    MANUAL("Man"), AUTOMATIC("Auto");
    private String id;

    TransmissionType(String s) {
        id = s;
    }

    /**
     * @return short string representing the transmission type
     */
    public String getId() {
        return id;
    }
}
