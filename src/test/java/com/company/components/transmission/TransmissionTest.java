package com.company.components.transmission;

import com.company.components.model.BodyType;
import org.junit.Assert;
import org.junit.Test;

public class TransmissionTest {
    @Test
    public void testAutomaticTransmission(){
        Transmission transmission=new Transmission(4,TransmissionType.AUTOMATIC);
        Assert.assertEquals("Auto-4",transmission.getInfo());
    }

    @Test
    public void testManualTransmission(){
        Transmission transmission=new Transmission(4,TransmissionType.MANUAL);
        Assert.assertEquals("Man-4",transmission.getInfo());
    }

    @Test
    public void testCompareTransmissionsAutomaticAndManual(){
        Transmission automatic=new Transmission(4,TransmissionType.AUTOMATIC);
        Transmission manual=new Transmission(4,TransmissionType.MANUAL);
        Assert.assertEquals(1,Transmission.compare(automatic,manual));
    }

    @Test
    public void testCompareSameTypeDifferentGears(){
        Transmission transmission1=new Transmission(6,TransmissionType.AUTOMATIC);
        Transmission transmission2=new Transmission(4,TransmissionType.AUTOMATIC);
        Assert.assertEquals(1,Transmission.compare(transmission1,transmission2));
    }

    @Test
    public void testAutomaticTransmissionType(){
        Transmission transmission=new Transmission(4,TransmissionType.AUTOMATIC);
        Assert.assertEquals("Auto",transmission.getType());
    }

    @Test
    public void testManualTransmissionType(){
        Transmission transmission=new Transmission(4,TransmissionType.MANUAL);
        Assert.assertEquals("Man",transmission.getType());
    }
}
