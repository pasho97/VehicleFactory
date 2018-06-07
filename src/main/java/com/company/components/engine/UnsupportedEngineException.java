package com.company.components.engine;

public class UnsupportedEngineException extends RuntimeException {
    UnsupportedEngineException(){
        super("Unsupported engine");
    }
}
