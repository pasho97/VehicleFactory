package com.company.vehicles;

import com.company.command.factories.CarFactory;
import com.company.command.factories.SuvFactory;
import com.company.components.engine.*;
import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.parsers.DisplacementEngineSpecificationsParser;
import com.company.components.engine.parsers.EngineParseException;
import com.company.components.engine.parsers.EngineTypeEngineSpecificationsParser;
import com.company.components.engine.parsers.HorsepowerEngineSpecificationsParser;
import com.company.components.engine.turbo.BasicTurbo;
import com.company.components.model.*;
import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionFactory;
import com.company.components.transmission.TransmissionType;
import com.company.components.transmission.UnsupportedTransmissionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SuvFactoryTest {
    private SuvFactory factory;
    @Before
    public void init(){
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
        EngineFactory engineFactory = factoryBuilder.createEngineFactory();

        TransmissionFactory transmissionFactory =new TransmissionFactory(Arrays.asList(
                new Transmission(4,TransmissionType.MANUAL),new Transmission(4,TransmissionType.AUTOMATIC),
                new Transmission(6,TransmissionType.MANUAL),new Transmission(6,TransmissionType.AUTOMATIC)));

        ModelFactory modelFactory=new ModelFactory(Arrays.asList(new CarModel("A4", BodyType.SEDAN),
                new CarModel("A4", BodyType.COMBI), new CarModel("A4", BodyType.HATCHBACK),
                new SuvModel("Q3")));

        factory=new SuvFactory(engineFactory,modelFactory,transmissionFactory);
    }

    @Test
    public void testCreateSuvCheckInfo(){
        Vehicle suv=factory.create("engine=E model=Q3","BG042GRAU5WE3PAS5");
        Assert.assertEquals("BG042GRAU5WE3PAS5 Q3 E-402hp-euro6",suv.getInfo());
    }

    @Test
    public void testCreateSuvIllegalArgs(){
        try {
            Vehicle suv=factory.create("engine-E model=Q3","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Illegal arguments",ex.getMessage());
        }

    }

    @Test
    public void testCreateSuvNoEngineInfo(){
        try {
            Vehicle suv=factory.create("model=Q3","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Cannot find engine information",ex.getMessage());
        }
    }

    @Test
    public void testCreateSuvNoModelInfo(){
        try {
            Vehicle suv=factory.create("engine=E","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Cannot find model information",ex.getMessage());
        }
    }

    @Test
    public void testCreateElectricalSuvWithTransmission(){
        try {
            Vehicle suv=factory.create("engine=E model=Q3 transmission=Auto-4","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Electrical engines can't have transmissions",ex.getMessage());
        }
    }

    @Test
    public void testCreateSuvUnsupportedTransmission(){
        try {
            Vehicle suv=factory.create("engine=B model=Q3 transmission=Auto-8","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (UnsupportedTransmissionException ex){
            Assert.assertEquals("Unsupported transmission",ex.getMessage());
        }
    }

    @Test
    public void testCreateSuvUnsupportedModel(){
        try {
            Vehicle suv=factory.create("engine=B model=A9 transmission=Auto-4","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (UnsupportedVehicleModelException ex){
            Assert.assertEquals("Unsupported model",ex.getMessage());
        }
    }

    @Test
    public void testCreateSuvUnsupportedEngine(){
        try {
            Vehicle suv=factory.create("engine=B-9hp-euro4 model=A4 transmission=Auto-4","BG042GRAU5WE3PAS5");
            Assert.fail();
        }
        catch (EngineParseException ex){
            Assert.assertEquals("Cannot find Engine specifications with the required values",ex.getMessage());
        }
    }
}
