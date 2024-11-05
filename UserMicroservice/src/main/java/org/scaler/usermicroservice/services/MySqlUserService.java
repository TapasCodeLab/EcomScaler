package org.scaler.usermicroservice.services;

import org.scaler.usermicroservice.dtos.LoginDto;
import org.scaler.usermicroservice.dtos.LogoutRequestDto;
import org.scaler.usermicroservice.dtos.SignupDto;
import org.scaler.usermicroservice.dtos.UserDto;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.exceptions.IncorrectPasswordException;
import org.scaler.usermicroservice.exceptions.UserDoesNotExistException;
import org.scaler.usermicroservice.models.Role;
import org.scaler.usermicroservice.models.Token;
import org.scaler.usermicroservice.models.User;
import org.scaler.usermicroservice.repositories.TokenRepository;
import org.scaler.usermicroservice.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
public class MySqlUserService implements UserService{

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    MySqlUserService(UserRepository userRepository,TokenRepository tokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public ResponseEntity<UserDto> signup(String name, String email, String password) throws EmailAlreadyExistsException{
        Optional<User> savedUser = userRepository.findByEmail(email);
        if(savedUser.isEmpty()){
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setHashedPassword(bCryptPasswordEncoder.encode(password));
//            user.setHashedPassword(password);
            user.setRoles(null);
            user.setIsEmailVerified(false);
            User newUser = userRepository.save(user);
            UserDto userDto = new UserDto();
            userDto.setName(newUser.getName());
            userDto.setEmail(newUser.getEmail());
            userDto.setRoles(newUser.getRoles());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else{
            throw new EmailAlreadyExistsException("Email id "+email+" already registered");
        }
    }

    public ResponseEntity<Token> login(String email, String password) throws UserDoesNotExistException, IncorrectPasswordException {
        Optional<User> savedUser = userRepository.findByEmail(email);
        if(savedUser.isEmpty()) {
            throw new UserDoesNotExistException("User with " + email + " is not registered.");
        } else{
            User user = savedUser.get();
            if(bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
                Token token = new Token();
                UUID uuid = UUID.randomUUID();
                token.setValue(uuid.toString());
                token.setUser(user);
                token.setExpiryAt(null);
                Token newToken = tokenRepository.save(token);
                return new ResponseEntity<>(newToken, HttpStatus.OK);
            } else {
                throw new IncorrectPasswordException("You have provided an incorrect password.");
            }
        }
    }

    public ResponseEntity<Void> logout(LogoutRequestDto logoutRequestDto){
        return null;
    }

    public UserDto validate(String token){
        return null;
    }
}
