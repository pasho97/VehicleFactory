package com.company.components.engine;

import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.parsers.DisplacementEngineSpecificationsParser;
import com.company.components.engine.parsers.EngineParseException;
import com.company.components.engine.parsers.EngineTypeEngineSpecificationsParser;
import com.company.components.engine.parsers.HorsepowerEngineSpecificationsParser;
import com.company.components.engine.turbo.BasicTurbo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class EngineFactoryTest {
    private EngineFactory factory;
    private final static int DELTA=1;
    @Before
    public void init() {
        EngineFactoryBuilder factoryBuilder = new EngineFactoryBuilder();
        EmissionStandard standard3 = new EuroEmissionStandard(3);
        EmissionStandard standard4 = new EuroEmissionStandard(4);
        EmissionStandard standard5 = new EuroEmissionStandard(5);
        EmissionStandard standard6 = new EuroEmissionStandard(6);

        factoryBuilder.setEmissionStandardFactory(new EmissionStandardFactory(Arrays.asList(standard3,
                standard4, standard5, standard6)));
        factoryBuilder.setEngineSpecificationsList(Arrays.asList(new DieselEngineSpecifications(2000, 100),
                new PetrolEngineSpecifications(1000, 50),
                new PetrolEngineSpecifications(2000, 100, BasicTurbo.getInstance()), new ElectricEngineSpecification(300)));
        factoryBuilder.setParsers(Arrays.asList(new DisplacementEngineSpecificationsParser("L"),
                new EngineTypeEngineSpecificationsParser(),
                new HorsepowerEngineSpecificationsParser("hp", 1)));
        factory = factoryBuilder.createEngineFactory();
    }

    @Test
    public void testGetPetrolEngineByHorsepowerPreservesKw() {
        Engine engine = factory.getEngine("B-134hpT");
        Assert.assertEquals(100, engine.getKwPower(),DELTA);
    }

    @Test
    public void testGetPetrolEngineByHorsepowerPreservesTurbo() {
        Engine engine = factory.getEngine("B-134hpT");
        Assert.assertEquals("T", engine.getTurboId());
    }


    @Test
    public void testGetPetrolEngineByHorsepowerPreservesType() {
        Engine engine = factory.getEngine("B-134hpT");
        Assert.assertEquals("B", engine.getTypeId());
    }

    @Test
    public void testGetPetrolEngineByHorsepowerPreservesEmissionStandard() {
        Engine engine = factory.getEngine("B-134hpT-euro5");
        Assert.assertEquals("euro5", engine.getEmissionStandard());
    }

    @Test
    public void testGetPetrolEngineByHorsepowerDefaultEmissionStandardIsLowest() {
        Engine engine = factory.getEngine("B-134hpT");
        Assert.assertEquals("euro3", engine.getEmissionStandard());
    }

    @Test(expected = EngineParseException.class)
    public void testUnsupportedPetrolEngineThrowsException() {
        Engine engine = factory.getEngine("B-34hpT");
        Assert.fail();
    }

    @Test
    public void testNoSpecificPowerRequiredGetsLowestPowerEngineSupported() {
        Assert.assertEquals(50, factory.getEngine("B").getKwPower(),DELTA);
    }


    @Test
    public void testNoSpecificPowerRequiredGetsLowestPowerEngineSupportedNoTurbo() {
        Assert.assertNull(factory.getEngine("B").getTurboId());
    }

    @Test
    public void testGetByDisplacementWorksWell() {
        Assert.assertEquals(50, factory.getEngine("B-1L").getKwPower(),DELTA);
    }

    @Test
    public void testGetByHorsepowerReturnsEngineWithTurboIfHorsepowerSameAsRequired() {
        Assert.assertEquals("T", factory.getEngine("B-134hp").getTurboId());
    }

    @Test
    public void testGetByDisplacement() {
        Assert.assertEquals(factory.getEngine("B-134hp").getInfo(), factory.getEngine("B-2LT").getInfo());
    }

    @Test
    public void testGetElectricalEngineTurbo() {
        Assert.assertNull(factory.getEngine("E").getTurboId());
    }

    @Test
    public void testGetElectricalEngineHorsepower() {
        Assert.assertEquals(300, factory.getEngine("E").getKwPower(),DELTA);
    }

    @Test
    public void testGetElectricalEngineHighestEmissionStandard() {
        Assert.assertEquals("euro6", factory.getEngine("E").getEmissionStandard());
    }
}
