package com.company.components.engine;

import com.company.components.engine.turbo.BasicTurbo;
import com.company.components.engine.turbo.Turbo;
import org.junit.Assert;
import org.junit.Test;

public class DieselEngineSpecificationsTest {
    private EngineSpecifications dieselEngineSpecs=new DieselEngineSpecifications(2000,100);

    @Test
    public void testGetDisplacement(){
        Assert.assertEquals(2000,dieselEngineSpecs.getDisplacement());
    }

    @Test
    public void testGetHorsepower(){
        Assert.assertEquals(134,dieselEngineSpecs.getHorsepower());
    }

    @Test
    public void testGetKwPower(){
        Assert.assertEquals(100,dieselEngineSpecs.getKwPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDisplacementUnder2000ThrowsException(){
        EngineSpecifications temp=new DieselEngineSpecifications(1000,100);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDisplacementOver6000ThrowsException(){
        EngineSpecifications temp=new DieselEngineSpecifications(7000,100);
        Assert.fail();
    }
    @Test
    public void testGetTurboId(){
        Turbo turbo=BasicTurbo.getInstance();
        EngineSpecifications temp=new PetrolEngineSpecifications(2000,100,turbo);
        Assert.assertEquals(turbo.getTypeId(),temp.getTurboId());
    }

    @Test
    public void testGetTurboIdNull(){
        Assert.assertNull(dieselEngineSpecs.getTurboId());
    }
}
