package com.company.vehicles;

import com.company.components.engine.EngineFactory;
import com.company.components.engine.EngineFactoryBuilder;
import com.company.components.engine.PetrolEngineSpecifications;
import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.parsers.DisplacementEngineSpecificationsParser;
import com.company.components.model.BodyType;
import com.company.components.model.CarModel;
import com.company.components.model.SuvModel;
import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class SuvTest {
    private EngineFactory factory = new EngineFactoryBuilder()
            .setEngineSpecificationsList(
                    Collections.singletonList(new PetrolEngineSpecifications(2000, 100)))
            .setEmissionStandardFactory(new EmissionStandardFactory(
                    Collections.singletonList(new EuroEmissionStandard(5))))
            .setParsers(
                    Collections.singletonList(new DisplacementEngineSpecificationsParser("L")))
            .createEngineFactory();

    private SuvVehicle suv = new SuvVehicle(factory.getEngine("B-2L-euro5")
            , new SuvModel("Q4"),
            new Transmission(4, TransmissionType.AUTOMATIC), "BG042GRAU5WE3PAS5");


    @Test
    public void testGetInfo() {
        Assert.assertEquals("BG042GRAU5WE3PAS5 Q4 B-134hp-euro5 Auto-4", suv.getInfo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCarModelThrowsException(){
        Vehicle suv = new SuvVehicle(factory.getEngine("B-2L-euro5")
                , new CarModel("A4",BodyType.HATCHBACK),
                new Transmission(4, TransmissionType.AUTOMATIC), "BG042GRAU5WE3PAS5");
        Assert.fail();
    }
}
