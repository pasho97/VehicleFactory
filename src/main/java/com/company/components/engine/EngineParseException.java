package com.company.components.engine;


public class EngineParseException extends RuntimeException {
    EngineParseException() {
        super("Unsupported engine requirements format");
    }

}
