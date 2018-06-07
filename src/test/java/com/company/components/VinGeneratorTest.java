package com.company.components;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class VinGeneratorTest {
    private VinGenerator generator =
            new VinGenerator("0123456789ABCDEFGHJKLMNPRSTUVWXYZ", Locale.US.getCountry(), 0);

    @Test
    public void testVinGeneratorCountryCode() {
        Assert.assertEquals(Locale.US.getCountry(), generator.generate().substring(0, 2));
    }

    @Test
    public void testVinGeneratorFactoryNumber() {
        Assert.assertEquals("0", generator.generate().substring(2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVinGeneratorInvalidCountryCodeException() {
        generator = new VinGenerator("asdasdasd", "tqqweqwe", 0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVinGeneratorInvalidFactoryIdNumberException() {
        generator=new VinGenerator("0123456789ABCDEFGHJKLMNPRSTUVWXYZ", Locale.US.getCountry(), 10);
        Assert.fail();
    }
}
