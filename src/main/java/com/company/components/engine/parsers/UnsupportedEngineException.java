package com.company.components.engine.parsers;

public class UnsupportedEngineException extends RuntimeException {
    UnsupportedEngineException(){
        super("Unsupported engine");
    }
}
