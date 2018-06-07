package com.company.components.engine.parsers;


public class EngineParseException extends RuntimeException {
    EngineParseException() {
        super("Cannot find Engine specifications with the required values");
    }

}
