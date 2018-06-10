package com.company.command.update.utils;

import com.company.components.engine.Engine;
import com.company.components.engine.EngineFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class EngineUpdateHelperTest {
    private EngineFactory factory=mock(EngineFactory.class);
    private UpdateHelper updateHelper =new EngineUpdateHelper(factory);
    private static final String DELIMITER="-";

    @Test
    public void testGetType(){
        Assert.assertEquals("engine", updateHelper.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringArrayWithInvalidSizeThrowsException(){
        updateHelper.getUpdatedComponentString("asdasd".split(DELIMITER),"asdasd",DELIMITER);
        Assert.fail();
    }

    @Test
    public void testUpdaterCallsEngineFactoryGetEngineMethod(){
        try {
            updateHelper.getUpdatedComponentString("a-b-c".split(DELIMITER),"a-b-c",DELIMITER);
        }
        catch (Exception ignored){}
        verify(factory,times(1)).getEngine(anyString());
    }

    @Test
    public void testUpdateReturnsEngineFactoryModifiedResultEngineGetInfoMethod(){
        Engine engine=mock(Engine.class);
        when(engine.getInfo()).thenReturn("test");
        when(factory.getEngine(anyString())).thenReturn(engine);
        Assert.assertEquals("engine='test'",
                updateHelper.getUpdatedComponentString("a-b-c".split(DELIMITER),"a-b-c",DELIMITER));

    }
}
