package org.scaler.usermicroservice.security.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.scaler.usermicroservice.models.User;
import org.scaler.usermicroservice.repositories.UserRepository;
import org.scaler.usermicroservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with email "+username+" not found");
        }
        return new CustomUserDetails(optionalUser.get());

    }
}
