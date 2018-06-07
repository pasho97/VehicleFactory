package com.company.components.engine.emission.standard;

import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class EmissionStandardFactoryTest {
    private final EmissionStandardFactory factory=new EmissionStandardFactory(Arrays.asList(new EuroEmissionStandard(1),
            new EuroEmissionStandard(2),
            new EuroEmissionStandard(3),
            new EuroEmissionStandard(4),
            new EuroEmissionStandard(5),
            new EuroEmissionStandard(6)));

    @Test
    public void testGetStandardInstance(){
        Assert.assertEquals("euro3",factory.getStandardInstance("B-147hp-euro3").getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstanceThrowsException(){
        factory.getStandardInstance("euro31");
        Assert.fail();
    }

    @Test
    public void testGetDefaultInstance(){
        Assert.assertEquals("euro1",factory.getStandardInstance("").getName());
    }
}
