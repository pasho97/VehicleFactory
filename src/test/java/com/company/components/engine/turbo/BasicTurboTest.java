package com.company.components.engine.turbo;

import org.junit.Assert;
import org.junit.Test;

public class BasicTurboTest {
    private final Turbo turbo=BasicTurbo.getInstance();
    @Test
    public void testGetTurboId(){
        Assert.assertEquals("T",turbo.getTypeId());
    }
    @Test
    public void testIncreaseKilowattsIfMounted(){
        Assert.assertEquals(30,turbo.getPowerIncreaseIfMounted(100));
    }

    @Test
    public void testDecreaseKilowattsIfDismounted(){
        Assert.assertEquals(30,turbo.getPowerDecreaseIfDismounted(130));
    }

}
