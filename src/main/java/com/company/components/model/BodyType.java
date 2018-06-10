package com.company.components.model;

/**
 * Body types in car models
 */
public enum BodyType {
    SEDAN, HATCHBACK, COMBI;

    @Override
    public String toString() {
        return "-" + super.toString().toLowerCase();
    }
}
