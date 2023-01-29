package org.example.cucumber.with.guice.framework.exception;

public class JsonReadWriteException extends RuntimeException{
    public JsonReadWriteException(final String message, final Throwable t){
        super(message, t);
    }

}
