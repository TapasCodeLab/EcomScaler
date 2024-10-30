package org.scaler.productmicroservice.exceptionhandler;

import org.scaler.productmicroservice.dto.CategoryNotFoundExceptionDto;
import org.scaler.productmicroservice.dto.ProductNotFoundExceptionDto;
import org.scaler.productmicroservice.exceptions.CategoryNotFoundException;
import org.scaler.productmicroservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){
        ProductNotFoundExceptionDto dto = new ProductNotFoundExceptionDto();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryNotFoundExceptionDto> handleCategoryNotFoundException(CategoryNotFoundException exception){
        CategoryNotFoundExceptionDto dto = new CategoryNotFoundExceptionDto();
        dto.setMessage(exception.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }



}
