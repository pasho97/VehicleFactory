package com.company.command;

import com.company.VehiclePersistentStorage;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class DisassembleCommandTest {
    private final VehiclePersistentStorage storageMock = mock(VehiclePersistentStorage.class);
    private Command disassembleCommand = new DisassembleCommand(storageMock);

    @Test
    public void testCallsPersistentStorageDisassembleMethod() {
        when(storageMock.disassemble(any())).thenReturn("something");
        disassembleCommand.interpret("test");
        verify(storageMock, times(1)).disassemble(any());
    }

    @Test
    public void testGetCommandName() {
        Assert.assertEquals("disassemble", disassembleCommand.getCommandName());
    }

    @Test
    public void testThrowsExceptionIfThereIsNoDeviceWithTheGivenVinToBeDisassembled(){
        try {
            disassembleCommand.interpret("Asdasdasdasdasd");
            Assert.fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Vehicle with this vin is already disassembled or does not exist",e.getMessage());
        }
    }


}
