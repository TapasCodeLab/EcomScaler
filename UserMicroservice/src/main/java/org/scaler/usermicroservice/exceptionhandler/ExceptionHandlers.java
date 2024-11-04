package org.scaler.usermicroservice.exceptionhandler;

import org.scaler.usermicroservice.dtos.EmailAlreadyExistExceptionDto;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<EmailAlreadyExistExceptionDto> EmailAlreadyExistsException(EmailAlreadyExistsException exception){
        EmailAlreadyExistExceptionDto dto = new EmailAlreadyExistExceptionDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
