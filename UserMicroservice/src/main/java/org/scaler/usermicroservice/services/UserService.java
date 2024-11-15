package org.scaler.usermicroservice.services;

import org.scaler.usermicroservice.dtos.*;
import org.scaler.usermicroservice.exceptions.*;
import org.scaler.usermicroservice.models.Token;
import org.scaler.usermicroservice.models.User;
import org.springframework.http.ResponseEntity;

import java.util.TooManyListenersException;


public interface UserService {

    public ResponseEntity<UserDto> signup(String name, String email, String password) throws EmailAlreadyExistsException;

    public ResponseEntity<TokenDto> login(String email, String password) throws UserDoesNotExistException, IncorrectPasswordException, TooManyActiveSessionException;

    public ResponseEntity<String> logout(String token_value);
    public ResponseEntity<UserDto> validate(String token) throws InvalidTokenException;
}
