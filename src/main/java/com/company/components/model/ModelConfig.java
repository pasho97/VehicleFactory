package com.company.components.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class used for {@link VehicleModel} beans
 */
@Configuration
public class ModelConfig {
    @Bean
    public VehicleModel A1S() {
        return new CarModel("A1", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A1H() {
        return new CarModel("A1", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A1C() {
        return new CarModel("A1", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A2S() {
        return new CarModel("A2", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A2H() {
        return new CarModel("A2", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A2C() {
        return new CarModel("A2", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A3S() {
        return new CarModel("A3", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A3H() {
        return new CarModel("A3", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A3C() {
        return new CarModel("A3", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A4S() {
        return new CarModel("A4", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A4H() {
        return new CarModel("A4", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A4C() {
        return new CarModel("A4", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A5S() {
        return new CarModel("A5", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A5H() {
        return new CarModel("A5", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A5C() {
        return new CarModel("A5", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A6S() {
        return new CarModel("A6", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A6H() {
        return new CarModel("A6", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A6C() {
        return new CarModel("A6", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A7S() {
        return new CarModel("A7", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A7H() {
        return new CarModel("A7", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A7C() {
        return new CarModel("A7", BodyType.COMBI);
    }

    @Bean
    public VehicleModel A8S() {
        return new CarModel("A8", BodyType.SEDAN);
    }

    @Bean
    public VehicleModel A8H() {
        return new CarModel("A8", BodyType.HATCHBACK);
    }

    @Bean
    public VehicleModel A8C() {
        return new CarModel("A8", BodyType.COMBI);
    }

    @Bean
    public VehicleModel Q1() {
        return new SuvModel("Q1");
    }

    @Bean
    public VehicleModel Q2() {
        return new SuvModel("Q2");
    }

    @Bean
    public VehicleModel Q3() {
        return new SuvModel("Q3");
    }

    @Bean
    public VehicleModel Q4() {
        return new SuvModel("Q4");
    }

    @Bean
    public VehicleModel Q5() {
        return new SuvModel("Q5");
    }

    @Bean
    public VehicleModel Q6() {
        return new SuvModel("Q6");
    }

    @Bean
    public VehicleModel Q7() {
        return new SuvModel("Q7");
    }

    @Bean
    public VehicleModel Q8() {
        return new SuvModel("Q8");
    }
}
