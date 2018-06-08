package com.company.command;

import com.company.VehiclePersistentStorage;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintCommandTest {
    private final VehiclePersistentStorage storage=mock(VehiclePersistentStorage.class);
    private Command printCommand=new PrintCommand(storage);

    @Test
    public void testGetCommandName(){
        Assert.assertEquals("print",printCommand.getCommandName());
    }

    @Test
    public void testPrintAllCallsGetAllInfoStorageMethod(){
        when(storage.getAllInfo()).thenReturn("allInfo method result returned");
        Assert.assertEquals("allInfo method result returned",printCommand.interpret("all"));
    }

    @Test
    public void testPrintByGivenVinCallsGetInfoByVinStorageMethod(){
        when(storage.getInfoByVin(anyString())).thenReturn("getInfoByVin method result returned");
        Assert.assertEquals("getInfoByVin method result returned",printCommand.interpret("thisIsAVin"));

    }
}
