package org.scaler.usermicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDoesNotExistExceptionDto {
    private String message;
}
