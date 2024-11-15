package org.scaler.usermicroservice.exceptionhandler;

import org.scaler.usermicroservice.dtos.*;
import org.scaler.usermicroservice.exceptions.*;
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

    @ExceptionHandler(TooManyActiveSessionException.class)
    public ResponseEntity<TooManyActiveSessionExceptionDto> TooManyActiveSessionException(TooManyActiveSessionException exception){
        TooManyActiveSessionExceptionDto dto = new TooManyActiveSessionExceptionDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<InvalidTokenExceptionDto> InvalidTokenException(InvalidTokenException exception){
        InvalidTokenExceptionDto dto = new InvalidTokenExceptionDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.FORBIDDEN);
    }

}
