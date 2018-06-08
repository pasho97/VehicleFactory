package com.company.command.update.utils;

import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TransmissionUpdateHelperTest {
    private TransmissionFactory factory=mock(TransmissionFactory.class);
    private UpdateHelper updateHelper =new TransmissionUpdateHelper(factory);
    private static final String DELIMITER="-";
    @Test
    public void testGetType(){
        Assert.assertEquals("transmission", updateHelper.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringArrayWithInvalidSizeThrowsException(){
        updateHelper.getUpdatedComponentString("asd-asd-asd".split(DELIMITER),"asdasd-a-b-c",DELIMITER);
        Assert.fail();
    }

    @Test
    public void testUpdaterCallsEngineFactoryGetEngineMethod(){
        try {
            updateHelper.getUpdatedComponentString("a-b".split(DELIMITER),"a-b",DELIMITER);
        }
        catch (Exception ignored){}
        verify(factory,times(1)).getTransmission(anyString());
    }

    @Test
    public void testUpdateReturnsEngineFactoryModifiedResultEngineGetInfoMethod(){
        Transmission transmission=mock(Transmission.class);
        when(transmission.getInfo()).thenReturn("test");
        when(factory.getTransmission(anyString())).thenReturn(transmission);
        Assert.assertEquals("transmission='test'",
                updateHelper.getUpdatedComponentString("a-b".split(DELIMITER),"a-b",DELIMITER));

    }
}
