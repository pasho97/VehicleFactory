package com.company.components.engine;

import org.junit.Assert;
import org.junit.Test;

public class ElectricEngineSpecificationsTest {
    private EngineSpecifications electricEngineSpecs=new ElectricEngineSpecification(100);

    @Test
    public void testTurboIsNull(){
        Assert.assertNull(electricEngineSpecs.getTurboId());
    }

    @Test
    public void testDisplacementIsZero(){
        Assert.assertEquals(0,electricEngineSpecs.getDisplacement());
    }

    @Test
    public void testGetHorsepower(){
        Assert.assertEquals(134, electricEngineSpecs.getHorsepower());
    }

    @Test
    public void testGetKwPower(){
        Assert.assertEquals(100, electricEngineSpecs.getKwPower(),1);
    }
}
