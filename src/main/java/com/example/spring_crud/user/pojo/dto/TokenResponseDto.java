package com.example.spring_crud.user.pojo.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Builder
@Value
public class TokenResponseDto {
    @NotNull
    String token;

    @NotNull
    String refreshToken;
}
