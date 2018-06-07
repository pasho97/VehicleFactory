package com.company.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class implementing a generator for VIN vehicle codes
 */
@Component
public class VinGenerator {
    private final char[] alphabet;
    private final Random random;

    /**
     * @param alphabet   String containing the symbols the VIN can have
     */
    @Autowired
    public VinGenerator(String alphabet) {
        this.alphabet = Arrays.stream(alphabet.split("")).distinct().collect(Collectors.joining()).toCharArray();
        random = new Random();
    }

    /**
     * @param countryISOCode ISO code of the country , should be 2 symbols long
     * @param factoryNumber number of the factory , should be between 0 and 9
     * @return 17 characters VIN , first 2 characters are the countryISOCode , 3rd character is the factoryNumber,
     * the 14 others are random
     * @throws IllegalArgumentException if countyISOCode or factoryNumber is invalid
     */
    public String generate(String countryISOCode, int factoryNumber) {
        if (countryISOCode.length() != 2) {
            throw new IllegalArgumentException("invalid country ISO code");
        }
        if (factoryNumber < 0 || factoryNumber > 9) {
            throw new IllegalArgumentException("invalid factory number , should be between 0 and 9");
        }

        return countryISOCode + factoryNumber + generate(14);
    }

    private String generate(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(alphabet[random.nextInt(alphabet.length)]);
        }

        return builder.toString();
    }
}
