package com.company.components.model;

import org.junit.Assert;
import org.junit.Test;

public class CarModelTest {
    private VehicleModel model = new CarModel("A2", BodyType.SEDAN);

    @Test
    public void testGetModel() {
        Assert.assertEquals("A2-sedan", model.getModel());
    }

    @Test
    public void testGetVehicleType() {
        Assert.assertEquals("car", model.getVehicleType());
    }

    @Test
    public void testGetModelHatchback() {
        Assert.assertEquals("A2-hatchback", new CarModel("A2", BodyType.HATCHBACK).getModel());
    }

    @Test
    public void testGetModelCombi() {
        Assert.assertEquals("A2-combi", new CarModel("A2", BodyType.COMBI).getModel());
    }
}
