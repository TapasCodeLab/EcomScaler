package org.scaler.usermicroservice.controllers;


import org.scaler.usermicroservice.dtos.LoginDto;
import org.scaler.usermicroservice.dtos.LogoutRequestDto;
import org.scaler.usermicroservice.dtos.SignupDto;
import org.scaler.usermicroservice.dtos.UserDto;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.models.Token;

import org.scaler.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Token login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        return userService.logout(logoutRequestDto);
    }

    @PostMapping("/validate/{token}")
    public UserDto validate(@PathVariable String token){
        return userService.validate(token);
    }


}
