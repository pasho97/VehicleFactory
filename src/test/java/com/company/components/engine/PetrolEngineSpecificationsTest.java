package com.company.components.engine;

import com.company.components.engine.turbo.BasicTurbo;
import com.company.components.engine.turbo.Turbo;
import org.junit.Assert;
import org.junit.Test;

public class PetrolEngineSpecificationsTest {
    private final static int DELTA=1;
    private EngineSpecifications petrolEngineSpecs =new PetrolEngineSpecifications(2000,100);
    @Test
    public void testGetDisplacement(){
        Assert.assertEquals(2000, petrolEngineSpecs.getDisplacement());
    }

    @Test
    public void testGetHorsepower(){
        Assert.assertEquals(134, petrolEngineSpecs.getHorsepower());
    }

    @Test
    public void testGetKwPower(){
        Assert.assertEquals(100, petrolEngineSpecs.getKwPower(),DELTA);
    }

    @Test
    public void testGetTurboId(){
        Turbo turbo=BasicTurbo.getInstance();
        EngineSpecifications temp=new PetrolEngineSpecifications(2000,100,turbo);
        Assert.assertEquals(turbo.getTypeId(),temp.getTurboId());
    }

    @Test
    public void testGetTurboIdNull(){
        Assert.assertNull(petrolEngineSpecs.getTurboId());
    }
}
