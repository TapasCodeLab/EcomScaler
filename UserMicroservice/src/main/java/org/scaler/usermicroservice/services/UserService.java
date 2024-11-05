package org.scaler.usermicroservice.services;

import org.scaler.usermicroservice.dtos.LoginDto;
import org.scaler.usermicroservice.dtos.LogoutRequestDto;
import org.scaler.usermicroservice.dtos.SignupDto;
import org.scaler.usermicroservice.dtos.UserDto;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.exceptions.IncorrectPasswordException;
import org.scaler.usermicroservice.exceptions.UserDoesNotExistException;
import org.scaler.usermicroservice.models.Token;
import org.scaler.usermicroservice.models.User;
import org.springframework.http.ResponseEntity;


public interface UserService {

    public ResponseEntity<UserDto> signup(String name, String email, String password) throws EmailAlreadyExistsException;

    public ResponseEntity<Token> login(String email, String password) throws UserDoesNotExistException, IncorrectPasswordException;

    public ResponseEntity<Void> logout(LogoutRequestDto logoutRequestDto);
    public UserDto validate(String token);
}
