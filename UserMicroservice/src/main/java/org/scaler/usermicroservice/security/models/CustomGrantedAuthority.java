package org.scaler.usermicroservice.security.models;

import org.scaler.usermicroservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    CustomGrantedAuthority(Role role){
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
