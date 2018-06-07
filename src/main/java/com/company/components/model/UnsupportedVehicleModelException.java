package com.company.components.model;

public class UnsupportedVehicleModelException extends RuntimeException {
    public UnsupportedVehicleModelException(){
        super("Unsupported model");
    }
}
