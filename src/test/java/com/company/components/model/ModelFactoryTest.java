package com.company.components.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ModelFactoryTest {
    private final ModelFactory factory=new ModelFactory(Arrays.asList(new CarModel("A4", BodyType.SEDAN),
            new CarModel("A4", BodyType.COMBI), new CarModel("A4", BodyType.HATCHBACK),
            new SuvModel("Q3")));

    @Test
    public void testGetCarModelSedan(){
        Assert.assertEquals("A4-sedan",factory.getModel("A4-sedan").getModel());
    }


    @Test
    public void testGetCarModelHatchback(){
        Assert.assertEquals("A4-hatchback",factory.getModel("A4-hatchback").getModel());
    }


    @Test
    public void testGetCarModelCombi(){
        Assert.assertEquals("A4-combi",factory.getModel("A4-combi").getModel());
    }

    @Test
    public void testGetDefaultSedanWhenAllTypesAreSupported(){
        Assert.assertEquals("A4-sedan",factory.getModel("A4").getModel());
    }

    @Test
    public void testGetDefaultWhenSedanUnsupported(){
        ModelFactory factory1=new ModelFactory(Arrays.asList(new CarModel("A4",BodyType.COMBI),
                new CarModel("A4",BodyType.HATCHBACK)));
        Assert.assertEquals("A4-hatchback",factory1.getModel("A4").getModel());
    }

    @Test
    public void testGetDefaultWhenSedanAndHatchbackUnsupporetd(){
        ModelFactory factory1=new ModelFactory(Collections.singletonList(new CarModel("A4", BodyType.COMBI)));
        Assert.assertEquals("A4-combi",factory1.getModel("A4").getModel());
    }

    @Test(expected = UnsupportedVehicleModelException.class)
    public void testThrowsExceptionWhenNoSuchModelIsSupported(){
        factory.getModel("A3");
    }


}
