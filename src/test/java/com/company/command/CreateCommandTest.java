package com.company.command;

import com.company.VehiclePersistentStorage;
import com.company.vehicles.factories.CarFactory;
import com.company.vehicles.factories.SuvFactory;
import com.company.vehicles.factories.VehicleFactory;
import com.company.components.VinGenerator;
import com.company.components.engine.*;
import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.emission.standards.EmissionStandardFactory;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.parsers.DisplacementEngineSpecificationsParser;
import com.company.components.engine.parsers.EngineTypeEngineSpecificationsParser;
import com.company.components.engine.parsers.HorsepowerEngineSpecificationsParser;
import com.company.components.engine.turbo.BasicTurbo;
import com.company.components.model.BodyType;
import com.company.components.model.CarModel;
import com.company.components.model.ModelFactory;
import com.company.components.model.SuvModel;
import com.company.components.transmission.Transmission;
import com.company.components.transmission.TransmissionFactory;
import com.company.components.transmission.TransmissionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CreateCommandTest {
    private CreateCommand createCommand;
    private VehiclePersistentStorage persistentStorage;

    @Before
    public void init() {

        persistentStorage = mock(VehiclePersistentStorage.class);
        when(persistentStorage.uniqueVin(any())).thenReturn(true);
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

        TransmissionFactory transmissionFactory = new TransmissionFactory(Arrays.asList(
                new Transmission(4, TransmissionType.MANUAL), new Transmission(4, TransmissionType.AUTOMATIC),
                new Transmission(6, TransmissionType.MANUAL), new Transmission(6, TransmissionType.AUTOMATIC)));

        ModelFactory modelFactory = new ModelFactory(Arrays.asList(new CarModel("A4", BodyType.SEDAN),
                new CarModel("A4", BodyType.COMBI), new CarModel("A4", BodyType.HATCHBACK),
                new SuvModel("Q3")));

        VehicleFactory factory = new CarFactory(engineFactory, modelFactory, transmissionFactory);
        VehicleFactory factory1 = new SuvFactory(engineFactory, modelFactory, transmissionFactory);

        VinGenerator generator = new VinGenerator("0123456789ABCDEFGHJKLMNPRSTUVWXYZ", "BG", 0);

        createCommand = new CreateCommand(Arrays.asList(factory, factory1), persistentStorage, generator);
    }

    @Test
    public void testCallsPersistentStorageStoreMethod() {
        createCommand.interpret("car engine=B-1L-euro4 model=A4 transmission=Auto-4");
        verify(persistentStorage, times(1)).store(any());
    }

    @Test
    public void testChecksIfGeneratedVinIsNotAlreadyAssignedInPersistentStorage() {
        createCommand.interpret("car engine=B-1L-euro4 model=A4 transmission=Auto-4");
        verify(persistentStorage, times(1)).uniqueVin(any());
    }

    @Test
    public void testPassesTheCreatedVehicleToStore() {
        Assert.assertEquals("A4-sedan B-67hp-euro4 Auto-4",
                createCommand.interpret("car engine=B-1L-euro4 model=A4 transmission=Auto-4")
                        .split(" ", 2)[1]);
    }

    @Test
    public void testCallsVehicleFactory() {
        VehicleFactory vehicleFactory = mock(VehicleFactory.class);
        when(vehicleFactory.getType()).thenReturn("car");
        when(vehicleFactory.create(any(), any())).thenThrow(new RuntimeException("vehicle factory create method called"));
        createCommand = new CreateCommand(Collections.singletonList(vehicleFactory), persistentStorage,
                new VinGenerator("a", "BG", 3));
        try {
            createCommand.interpret("car asdasdasd");

            Assert.fail();
        } catch (RuntimeException e) {

            Assert.assertEquals("vehicle factory create method called", e.getMessage());
        }

    }

    @Test
    public void testChecksIfVehicleFactoryTypeIsTheSameAsRequired() {
        VehicleFactory vehicleFactory = mock(VehicleFactory.class);
        when(vehicleFactory.getType()).thenReturn("test");
        createCommand = new CreateCommand(Collections.singletonList(vehicleFactory), persistentStorage,
                new VinGenerator("a", "BG", 3));
        try {
            createCommand.interpret("test asdasdasd");
        } catch (Exception ignored) { }

        verify(vehicleFactory, times(1)).create(any(), any());
    }

    @Test
    public void testGeneratesNewVinUntilUniqueVinIsGenerated(){
        when(persistentStorage.uniqueVin(any())).thenReturn(false,false,true);
        createCommand.interpret("car engine=B-1L-euro4 model=A4 transmission=Auto-4");

        verify(persistentStorage,times(3)).uniqueVin(any());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionIfCreateTypeArgIsUnsupported(){
        createCommand.interpret("bike this is a test");

    }

    @Test
    public void testGetCommandName() {
        Assert.assertEquals("create", createCommand.getCommandName());
    }
}
