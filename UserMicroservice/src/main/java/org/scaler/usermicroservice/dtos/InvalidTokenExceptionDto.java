package org.scaler.usermicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidTokenExceptionDto {
    private String message;
}
