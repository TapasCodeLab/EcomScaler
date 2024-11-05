package org.scaler.usermicroservice.exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException(String message){
        super(message);
    }
}
