package com.company.components.engine.model;

import com.company.components.model.SuvModel;
import com.company.components.model.VehicleModel;
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
