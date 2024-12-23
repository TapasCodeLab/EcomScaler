package org.scaler.usermicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TokenDto {
    private String value;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiryAt;
}
