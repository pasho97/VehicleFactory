package com.company.components;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class implementing a generator for VIN vehicle codes
 */
public class VinGenerator {
    private final char[] alphabet;
    private final Random random;
    private String countryIsoCode;
    private int factoryNumberId;

    /**
     * @param alphabet       String containing the symbols the VIN can have
     * @param countryIsoCode ISO code of the country the factory resides in, should be 2 symbols long
     * @param factoryNumber  number of the factory it will be used in, should be between 0 and 9
     */

    public VinGenerator(String alphabet, String countryIsoCode, int factoryNumber) {
        this.countryIsoCode = countryIsoCode;
        this.factoryNumberId = factoryNumber;
        if (countryIsoCode.length() != 2) {
            throw new IllegalArgumentException("invalid country ISO code");
        }
        if (factoryNumberId < 0 || factoryNumberId > 9) {
            throw new IllegalArgumentException("invalid factory number , should be between 0 and 9");
        }
        this.alphabet = Arrays.stream(alphabet.split("")).distinct().collect(Collectors.joining()).toCharArray();
        random = new Random();
    }

    /**
     * @return 17 characters VIN , first 2 characters are the countryISOCode , 3rd character is the factoryNumber,
     * the 14 others are random
     * @throws IllegalArgumentException if countyISOCode or factoryNumber is invalid
     */
    public String generate() {
        return countryIsoCode + factoryNumberId + generate(14);
    }

    private String generate(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(alphabet[random.nextInt(alphabet.length)]);
        }

        return builder.toString();
    }
}
