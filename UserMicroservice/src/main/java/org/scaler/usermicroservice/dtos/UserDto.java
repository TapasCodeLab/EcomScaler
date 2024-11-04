package org.scaler.usermicroservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.scaler.usermicroservice.models.Role;
import org.scaler.usermicroservice.models.User;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

//    public static UserDto from(User user){
//        UserDto userDto = new UserDto();
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setRoles(user.getRoles());
//        return userDto;
//    }
}
