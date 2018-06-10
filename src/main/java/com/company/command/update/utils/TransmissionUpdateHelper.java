package com.company.command.update.utils;

import com.company.components.transmission.TransmissionFactory;
import org.springframework.stereotype.Component;

@Component
public class TransmissionUpdateHelper implements UpdateHelper {
    private final TransmissionFactory transmissionFactory;

    /**
     * @param transmissionFactory factory used for validating and eventually returning the requested transmission component
     */
    public TransmissionUpdateHelper(TransmissionFactory transmissionFactory) {
        this.transmissionFactory = transmissionFactory;
    }

    @Override
    public String getUpdatedComponentString(String currentTransmissionArgs[], String inputArgs, String delimiter) {
        String[] newTransmissionArgs = inputArgs.split(delimiter);
        if (newTransmissionArgs.length >= 3) {
            throw new IllegalArgumentException("Unsupported transmission update");
        }
        if (newTransmissionArgs.length == 1) {
            String temp = newTransmissionArgs[0];
            newTransmissionArgs = new String[2];
            newTransmissionArgs[0] = temp;
            newTransmissionArgs[1] = "";
        }
        for (int i = 0; i < currentTransmissionArgs.length; i++) {
            if (newTransmissionArgs[i].equals("")) {
                newTransmissionArgs[i] = currentTransmissionArgs[i];
            }
        }

        return "transmission='" + transmissionFactory.getTransmission(
                String.join(delimiter, newTransmissionArgs)).getInfo() + "'";
    }

    @Override
    public String getType() {
        return "transmission";
    }
}
