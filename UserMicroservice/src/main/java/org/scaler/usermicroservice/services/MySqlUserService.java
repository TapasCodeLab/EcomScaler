package org.scaler.usermicroservice.services;

import org.scaler.usermicroservice.dtos.*;
import org.scaler.usermicroservice.exceptions.EmailAlreadyExistsException;
import org.scaler.usermicroservice.exceptions.IncorrectPasswordException;
import org.scaler.usermicroservice.exceptions.TooManyActiveSessionException;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    public ResponseEntity<TokenDto> login(String email, String password) throws UserDoesNotExistException, IncorrectPasswordException,TooManyActiveSessionException {
        Optional<User> savedUser = userRepository.findByEmail(email);
        if(savedUser.isEmpty()) {
            throw new UserDoesNotExistException("User with " + email + " is not registered.");
        } else{
            User user = savedUser.get();
            if(bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
                //int numberOfActiveSession = tokenRepository.countTokenByUserId(user.getId());
                int numberOfActiveSession = tokenRepository.countActiveTokenForUser(user.getId());
                if(numberOfActiveSession>=2){
                    throw new TooManyActiveSessionException("Two or more active session present for :"+email);
                }
                Token token = generateToken(user);
                Token newToken = tokenRepository.save(token);
                TokenDto tokenDto = new TokenDto();
                tokenDto.setValue(newToken.getValue());
                tokenDto.setName(newToken.getUser().getName());
                tokenDto.setEmail(newToken.getUser().getEmail());
                tokenDto.setCreatedAt(newToken.getCreatedAt());
                tokenDto.setExpiryAt(newToken.getExpiryAt());
                return new ResponseEntity<>(tokenDto, HttpStatus.OK);
            } else {
                throw new IncorrectPasswordException("You have provided an incorrect password.");
            }
        }
    }

    private Token generateToken(User user){
        Token token = new Token();
        UUID uuid = UUID.randomUUID();
        token.setValue(uuid.toString());
        token.setUser(user);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime fifteenMinsFromNow = currentDateTime.plusMinutes(15L);
        token.setCreatedAt(currentDateTime);
        token.setExpiryAt(fifteenMinsFromNow);
        return token;
    }

    public ResponseEntity<String> logout(String token_value){
        Optional<Token> savedToken = tokenRepository.findTokenByValueAndDeleted(token_value, false);
        if(savedToken.isEmpty()){
            return new ResponseEntity<>("No active session found.", HttpStatus.NOT_FOUND);
        }else {
            Token token = savedToken.get();
            token.setDeleted(true);
            tokenRepository.save(token);
            return new ResponseEntity<>("Session logged out successfully", HttpStatus.OK);
        }
    }

    public ResponseEntity<UserDto> validate(String token){
        Optional<Token> savedToken = tokenRepository.findTokenByValueAndDeletedAndExpiryAtAfter(token, false, LocalDateTime.now());
        if(savedToken.isEmpty()){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            User newUser = savedToken.get().getUser();
            UserDto userDto = new UserDto();
            userDto.setName(newUser.getName());
            userDto.setEmail(newUser.getEmail());
            userDto.setRoles(newUser.getRoles());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
    }
}
