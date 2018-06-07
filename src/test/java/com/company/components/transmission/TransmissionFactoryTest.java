package com.company.components.transmission;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TransmissionFactoryTest {
    private static final TransmissionFactory transmissionFactory =new TransmissionFactory(Arrays.asList(
            new Transmission(4,TransmissionType.MANUAL),new Transmission(4,TransmissionType.AUTOMATIC),
            new Transmission(6,TransmissionType.MANUAL),new Transmission(6,TransmissionType.AUTOMATIC)));

    @Test
    public void testGetAutoDefaultReturnsLowestSupportedGear(){
        Assert.assertEquals("Auto-4", transmissionFactory.getTransmission("Auto").getInfo());
    }

    @Test
    public void testGetDefaultAutoWhenThereIsNoManual(){
        TransmissionFactory factory1=new TransmissionFactory(Arrays.asList(
                new Transmission(4,TransmissionType.AUTOMATIC),new Transmission(5,TransmissionType.AUTOMATIC)));
        Assert.assertEquals("Auto-4",factory1.getTransmission(null).getInfo());
    }

    @Test
    public  void testGetDefault(){
        Assert.assertEquals("Man-4", transmissionFactory.getTransmission(null).getInfo());
    }

    @Test(expected = UnsupportedTransmissionException.class)
    public void testGetDefaultAutoWhenAutomaticTransmissionsNotSupported(){
        TransmissionFactory factory1=new TransmissionFactory(Arrays.asList(
                new Transmission(4,TransmissionType.MANUAL),new Transmission(5,TransmissionType.MANUAL)));
        factory1.getTransmission("Auto");
        Assert.fail();
    }

    @Test(expected = UnsupportedTransmissionException.class)
    public void testGetDefaultAutoWhenManualTransmissionsNotSupported(){
        TransmissionFactory factory1=new TransmissionFactory(Arrays.asList(
                new Transmission(4,TransmissionType.AUTOMATIC),new Transmission(5,TransmissionType.AUTOMATIC)));
        factory1.getTransmission("Man");
        Assert.fail();
    }

    @Test(expected = UnsupportedTransmissionException.class)
    public void testSpecificTransmissionNotSupportedThrowsException(){
        transmissionFactory.getTransmission("Auto-8");
        Assert.fail();
    }
}
