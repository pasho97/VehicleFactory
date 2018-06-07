package com.company.components.transmission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *  Spring configuration class used for {@link Transmission} beans
 */
@Configuration
public class TransmissionConfig {
    @Bean
    public Transmission auto4() {
        return new Transmission(4,TransmissionType.AUTOMATIC);
    }
    @Bean
    public Transmission auto5() {
        return new Transmission(5,TransmissionType.AUTOMATIC);
    }
    @Bean
    public Transmission auto6() {
        return new Transmission(6,TransmissionType.AUTOMATIC);
    }
    @Bean
    public Transmission auto8() {
        return new Transmission(8,TransmissionType.AUTOMATIC);
    }

    @Bean
    public Transmission man4() {
        return new Transmission(4,TransmissionType.MANUAL);
    }
    @Bean
    public Transmission man5() {
        return new Transmission(5,TransmissionType.MANUAL);
    }
    @Bean
    public Transmission man6() {
        return new Transmission(6,TransmissionType.MANUAL);
    }
}
