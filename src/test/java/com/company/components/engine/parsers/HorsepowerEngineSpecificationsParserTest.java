package com.company.components.engine.parsers;

import com.company.components.engine.DieselEngineSpecifications;
import com.company.components.engine.EngineSpecifications;
import com.company.components.engine.PetrolEngineSpecifications;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class HorsepowerEngineSpecificationsParserTest {
    private final EngineSpecificationsParser parser = new HorsepowerEngineSpecificationsParser("hp", 1);

    @Test
    public void testHaveRequestedPower() {

        Assert.assertTrue(parser.haveRequestedPower(
                new DieselEngineSpecifications(2000, 100), "134hp"));
    }

    @Test
    public void testHaveRequestedPowerFalse() {

        Assert.assertFalse(parser.haveRequestedPower(
                new DieselEngineSpecifications(2000, 100), "100hp"));
    }

    @Test
    public void testCanParse() {
        Assert.assertTrue(parser.canParse("B-100hp-euro3"));
    }

    @Test
    public void testCanParseFalse() {
        Assert.assertFalse(parser.canParse("B-6LT-euro3"));
    }

    @Test
    public void testEnginePowerValueTypeId() {
        Assert.assertEquals("hp", parser.getEnginePowerValueTypeIdentifier());
    }

    @Test
    public void testParse() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        Assert.assertEquals(specs.getKwPower(), parser.parse("B-134hp", Collections.singletonList(specs)).getKwPower());
    }

    @Test
    public void testParseGetsSpecsTurboEvenIfNotExplicitlyRequired() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        Assert.assertEquals(specs.getTurboId(), parser.parse("B-134hp", Collections.singletonList(specs)).getTurboId());
    }

    @Test(expected = EngineParseException.class)
    public void testParseDoesNotGetSpecsWithoutTurboWhenTurboIsExplicitlyRequired() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        parser.parse("B-134hpT-euro5", Collections.singletonList(specs));

        Assert.fail();
    }
}
