package com.company.components.engine.emission.standards;

public class UnsupportedEmissionStandardException extends RuntimeException {
    UnsupportedEmissionStandardException(){
        super("Unsupported emission standard");
    }
}
