package org.scaler.usermicroservice.exceptionhandler;

import org.scaler.usermicroservice.dtos.EmailAlreadyExistExceptionDto;
import org.scaler.usermicroservice.dtos.IncorrectPasswordExceptionDto;
import org.scaler.usermicroservice.dtos.UserDoesNotExistExceptionDto;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.exceptions.IncorrectPasswordException;
import org.scaler.usermicroservice.exceptions.UserDoesNotExistException;
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

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<UserDoesNotExistExceptionDto> UserDoesNotExistException(UserDoesNotExistException exception){
        UserDoesNotExistExceptionDto dto = new UserDoesNotExistExceptionDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<IncorrectPasswordExceptionDto> IncorrectPasswordException(IncorrectPasswordException exception){
        IncorrectPasswordExceptionDto dto = new IncorrectPasswordExceptionDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


}
