package com.company.command;

import com.company.VehiclePersistentStorage;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class FindCommandTest {
    private final VehiclePersistentStorage storage=mock(VehiclePersistentStorage.class);
    private final Command findCommand=new FindCommand(storage);

    @Test
    public void testGetCommandName() {
        Assert.assertEquals("disassemble", findCommand.getCommandName());
    }

    @Test
    public void testReturnsPersistentStorageFindByEmissionStandardMethodResult(){
        when(storage.getByEmissionStandard(anyString())).thenReturn("find by emission standard method called");
        Assert.assertEquals("find by emission standard method called",findCommand.interpret("test"));
    }

}
