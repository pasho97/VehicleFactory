package com.company.components.engine;

import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.turbo.BasicTurbo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EngineTest {
    private Engine engine;

    @Before
    public void init() {
        engine = new Engine(new DieselEngineSpecifications(2000, 100, BasicTurbo.getInstance()), new EuroEmissionStandard(4));
    }

    @Test
    public void testEngineGetTurbo() {
        Assert.assertEquals(BasicTurbo.getInstance().getTypeId(), engine.getTurboId());
    }

    @Test
    public void testEngineGetTurboNull() {
        engine = new Engine(new DieselEngineSpecifications(2000, 100), new EuroEmissionStandard(3));
        Assert.assertNull(engine.getTurboId());
    }

    @Test
    public void testGetEmissionStandard() {
        Assert.assertEquals("euro4", engine.getEmissionStandard());
    }

    @Test
    public void testMountTurbo() {
        engine = new Engine(new DieselEngineSpecifications(2000, 100), new EuroEmissionStandard(3));
        engine.mountTurbo(BasicTurbo.getInstance());
        Assert.assertEquals(130, engine.getKwPower());
    }

    @Test
    public void testMountTurboNull() {
        engine = new Engine(new DieselEngineSpecifications(2000, 100), new EuroEmissionStandard(3));
        engine.mountTurbo(null);
        Assert.assertEquals(100, engine.getKwPower());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMountTurboElectricEngine() {
        engine = new Engine(new ElectricEngineSpecification(400), new EuroEmissionStandard(6));
        engine.mountTurbo(BasicTurbo.getInstance());
        Assert.fail();
    }
}
