package com.company.components.engine.parsers;

import com.company.components.engine.DieselEngineSpecifications;
import com.company.components.engine.EngineSpecifications;
import com.company.components.engine.PetrolEngineSpecifications;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class DisplacementEngineSpecificationsParserTest {
    private final EngineSpecificationsPowerValueParser parser = new DisplacementEngineSpecificationsParser("L");
    private static final int DELTA=1;

    @Test
    public void testHaveRequestedPower() {

        Assert.assertTrue(parser.haveRequestedPower(
                new DieselEngineSpecifications(2000, 100), "2L"));
    }

    @Test
    public void testHaveRequestedPowerFalse() {

        Assert.assertFalse(parser.haveRequestedPower(
                new DieselEngineSpecifications(2000, 100), "1L"));
    }

    @Test
    public void testCanParse() {
        Assert.assertTrue(parser.canParse("B-2L-euro3"));
    }

    @Test
    public void testCanParseFalse() {
        Assert.assertFalse(parser.canParse("B-134hpT-euro3"));
    }

    @Test
    public void testEnginePowerValueTypeId() {
        Assert.assertEquals("L", parser.getEnginePowerValueTypeIdentifier());
    }

    @Test
    public void testParse() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        Assert.assertEquals(specs.getKwPower(),
                parser.parse("B-2L", Collections.singletonList(specs)).getKwPower(),DELTA);
    }

    @Test
    public void testParseGetsSpecsTurboEvenIfNotExplicitlyRequired() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        Assert.assertEquals(specs.getTurboId(), parser.parse("B-2L", Collections.singletonList(specs)).getTurboId());
    }

    @Test(expected = EngineParseException.class)
    public void testParseDoesNotGetSpecsWithoutTurboWhenTurboIsExplicitlyRequired() {
        EngineSpecifications specs = new PetrolEngineSpecifications(2000, 100);
        parser.parse("B-2LT-euro5", Collections.singletonList(specs));

        Assert.fail();
    }
}
