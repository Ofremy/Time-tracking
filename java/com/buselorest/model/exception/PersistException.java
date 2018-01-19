package com.buselorest.model.exception;

public class PersistException extends Exception {
    public PersistException(){

    }

    public PersistException(String message){super(message);}

    public PersistException(String message, Throwable throwable){super(message, throwable);}

    public PersistException(Throwable cause){super(cause);}

}
