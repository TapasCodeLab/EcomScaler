package org.scaler.productmicroservice.commons;

import org.scaler.productmicroservice.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){
        try {
            ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8081/user/validate/" + tokenValue, UserDto.class);
            return responseEntity.getBody();
        }
        catch (Exception e){
            return null;

        }
    }

}
