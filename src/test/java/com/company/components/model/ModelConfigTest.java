package com.company.components.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ModelConfigTest {
    private static final ApplicationContext context=new AnnotationConfigApplicationContext(ModelConfig.class);

    @Test
    public void testA1Sedan(){
        Assert.assertEquals("A1-sedan",((VehicleModel)context.getBean("A1S")).getModel());
    }
    @Test
    public void testA1Hatchback(){
        Assert.assertEquals("A1-hatchback",((VehicleModel)context.getBean("A1H")).getModel());
    }
    @Test
    public void testA1Combi(){
        Assert.assertEquals("A1-combi",((VehicleModel)context.getBean("A1C")).getModel());
    }
    @Test
    public void testQ1(){
        Assert.assertEquals("Q1",((VehicleModel)context.getBean("Q1")).getModel());
    }
}
