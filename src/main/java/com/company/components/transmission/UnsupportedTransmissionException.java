package com.company.components.transmission;

public class UnsupportedTransmissionException extends RuntimeException {
    UnsupportedTransmissionException() {
        super("Unsupported transmission");
    }
}
