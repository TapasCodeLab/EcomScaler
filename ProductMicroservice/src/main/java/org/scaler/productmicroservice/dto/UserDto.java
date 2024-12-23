package org.scaler.productmicroservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.scaler.productmicroservice.models.Role;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}
