package com.example.spring_crud.user.pojo.dto;

import lombok.*;

import javax.validation.constraints.*;

/**
 * @apiNote
 * 회원가입 요청값에 대한 DTO
 */
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Value
public class UserRegisterRequestDto {

    @NonNull @NotNull
    @Getter
    //최소 8 자, 하나 이상의 문자와 하나의 숫자 정규식
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @Size(min = 6, max = 15)
    private final String username;

    @NonNull @NotNull
    @Getter
    //최소 8 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 정규식
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @Size(min = 8, max = 20)
    private final String password;

    @NonNull @NotNull
    @Getter
    @Size(min = 2, max = 20)
    private String name;
}
