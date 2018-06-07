package com.company.components.engine;

import com.company.components.engine.emission.standards.EmissionStandard;
import com.company.components.engine.emission.standards.EuroEmissionStandard;
import com.company.components.engine.parsers.DisplacementEngineSpecificationsParser;
import com.company.components.engine.parsers.EngineSpecificationsParser;
import com.company.components.engine.parsers.HorsepowerEngineSpecificationsParser;
import com.company.components.engine.turbo.BasicTurbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineFactoryConfig {

    @Bean
    EngineSpecifications electricEngine() {
        return new ElectricEngineSpecification(432);
    }

    @Bean
    EngineSpecificationsParser displacementParser() {
        return new DisplacementEngineSpecificationsParser("L");
    }

    @Bean
    EngineSpecificationsParser horsepowerParser() {
        return new HorsepowerEngineSpecificationsParser("hp", 1);
    }

    @Bean
    EmissionStandard euro6() {
        return new EuroEmissionStandard(6);
    }

    @Bean
    EmissionStandard euro5() {
        return new EuroEmissionStandard(5);
    }

    @Bean
    EmissionStandard euro4() {
        return new EuroEmissionStandard(4);
    }

    @Bean
    EmissionStandard euro3() {
        return new EuroEmissionStandard(3);
    }

    @Bean
    EngineSpecifications petrolEngine1() {
        return new PetrolEngineSpecifications(1000, 55);
    }

    @Bean
    EngineSpecifications petrolEngine2() {
        return new PetrolEngineSpecifications(2000, 147, BasicTurbo.getInstance());
    }

    @Bean
    EngineSpecifications petrolEngine3() {
        return new PetrolEngineSpecifications(3000, 245);
    }

    @Bean
    EngineSpecifications petrolEngine4() {
        return new PetrolEngineSpecifications(4000, 253);
    }

    @Bean
    EngineSpecifications petrolEngine5() {
        return new PetrolEngineSpecifications(5000, 331);
    }

    @Bean
    EngineSpecifications petrolEngine6() {
        return new PetrolEngineSpecifications(6000, 510);
    }

    @Bean
    EngineSpecifications petrolEngine7() {
        return new PetrolEngineSpecifications(8000, 736);
    }

    @Bean
    EngineSpecifications dieselEngine3() {
        return new DieselEngineSpecifications(2000, (int) (147 * 1.3), BasicTurbo.getInstance());
    }

    @Bean
    EngineSpecifications dieselEngine2() {
        return new DieselEngineSpecifications(2000, 147);
    }

    @Bean
    EngineSpecifications dieselEngine7() {
        return new DieselEngineSpecifications(3000, 245);
    }

    @Bean
    EngineSpecifications dieselEngine4() {
        return new DieselEngineSpecifications(4000, 253);
    }

    @Bean
    EngineSpecifications dieselEngine5() {
        return new DieselEngineSpecifications(5000, 331);
    }

    @Bean
    EngineSpecifications dieselEngine6() {
        return new DieselEngineSpecifications(6000, 510);
    }

}
