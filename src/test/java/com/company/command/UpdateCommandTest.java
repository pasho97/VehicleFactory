package com.company.command;

import com.company.VehiclePersistentStorage;
import com.company.command.update.utils.UpdateHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UpdateCommandTest {
    private VehiclePersistentStorage storage=mock(VehiclePersistentStorage.class);
    private UpdateHelper updateHelper = mock(UpdateHelper.class);
    private Command updateCommand;
    private static final String DELIMITER="-";

    @Test
    public void testGetCommandName(){
        updateCommand=new UpdateCommand(storage,Collections.singletonList(updateHelper),DELIMITER);
        Assert.assertEquals("update",updateCommand.getCommandName());
    }

    @Test
    public void testCallsPersistentStorageUpdateByVinMethod(){
        when(storage.getColByVin(anyString(),anyString())).thenReturn("");
        doThrow(new RuntimeException("updateByVinMethod called")).when(storage).updateByVin(anyString(),anyString());
        when(updateHelper.getType()).thenReturn("test");
        updateCommand = new UpdateCommand(storage, Collections.singletonList(updateHelper),DELIMITER);
        try {
            updateCommand.interpret("dasdasdas test=-dasdasd");
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals("updateByVinMethod called",e.getMessage());
        }
    }

    @Test
    public void testThrowsExceptionIfTheDesiredUpdateIsUnsupported(){
        updateCommand = new UpdateCommand(storage, Collections.singletonList(updateHelper),DELIMITER);
        try {
            updateCommand.interpret("testVin testUpdateOperation=someArgs");
            Assert.fail();
        }catch (UnsupportedOperationException e){
            Assert.assertEquals("unsupported update operation",e.getMessage());
        }
    }
    @Test
    public void testInvalidFormatThrowsExceptionIfTheDesiredUpdateIsUnsupported(){
        updateCommand = new UpdateCommand(storage, Collections.singletonList(updateHelper),DELIMITER);
        try {
            updateCommand.interpret("testVin testUpdateOperationsomeArgs");
            Assert.fail();
        }catch (IllegalArgumentException e){
            Assert.assertEquals("Illegal update arguments format",e.getMessage());
        }
    }
}
