package com.company.components.model;

import org.junit.Assert;
import org.junit.Test;

public class SuvModelTest {
    private VehicleModel model=new SuvModel("Q1");

    @Test
    public void testGetModel(){
        Assert.assertEquals("Q1",model.getModel());
    }

    @Test
    public void testGetVehicleType(){
        Assert.assertEquals("suv",model.getVehicleType());
    }
}
