package com.company.components.transmission;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransmissionConfigTest {
    private static final ApplicationContext context=new AnnotationConfigApplicationContext(TransmissionConfig.class);

    @Test
    public void testAuto4Bean(){
        Assert.assertEquals("Auto-4",context.getBean("auto4",Transmission.class).getInfo());
    }
    @Test
    public void testMan4Bean(){
        Assert.assertEquals("Man-4",context.getBean("man4",Transmission.class).getInfo());
    }

}
