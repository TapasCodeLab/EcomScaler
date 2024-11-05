package org.scaler.usermicroservice.controllers;


import org.scaler.usermicroservice.dtos.*;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.exceptions.IncorrectPasswordException;
import org.scaler.usermicroservice.exceptions.TooManyActiveSessionException;
import org.scaler.usermicroservice.exceptions.UserDoesNotExistException;
import org.scaler.usermicroservice.models.Token;

import org.scaler.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TooManyListenersException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) throws EmailAlreadyExistsException {
        return userService.signup(signupDto.getName(), signupDto.getEmail(), signupDto.getPassword());
    }


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws UserDoesNotExistException, IncorrectPasswordException, TooManyActiveSessionException {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        return userService.logout(logoutRequestDto.getToken());
    }

    @PostMapping("/validate")
    public ResponseEntity<UserDto> validate(@RequestBody TokenDto tokenDto){
        return userService.validate(tokenDto.getValue());
    }


}
