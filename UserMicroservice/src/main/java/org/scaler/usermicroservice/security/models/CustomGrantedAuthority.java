package org.scaler.usermicroservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.scaler.usermicroservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority(){}

    CustomGrantedAuthority(Role role){
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
