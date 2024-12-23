package org.scaler.productmicroservice.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message){
        super(message);
    }
}
