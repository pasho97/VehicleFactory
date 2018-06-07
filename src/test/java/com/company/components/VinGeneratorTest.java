package com.company.components;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class VinGeneratorTest {
    private static final VinGenerator generator=new VinGenerator("0123456789ABCDEFGHJKLMNPRSTUVWXYZ");

    @Test
    public void testVinGeneratorCountryCode(){
        Assert.assertEquals(Locale.US.getCountry(),generator.generate(Locale.US.getCountry(),0).substring(0,2));
    }
    @Test
    public void testVinGeneratorFactoryNumber(){
        Assert.assertEquals("0",generator.generate(Locale.US.getCountry(),0).substring(2,3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVinGeneratorInvalidCountryCodeException(){
        generator.generate("abv",0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVinGeneratorInvalidFactoryIdNumberException(){
        generator.generate(Locale.US.getCountry(),10);
        Assert.fail();
    }
}
