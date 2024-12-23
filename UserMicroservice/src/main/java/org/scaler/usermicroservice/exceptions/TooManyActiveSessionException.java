package org.scaler.usermicroservice.exceptions;

public class TooManyActiveSessionException extends Exception{
    public TooManyActiveSessionException(String message){
        super(message);
    }
}
