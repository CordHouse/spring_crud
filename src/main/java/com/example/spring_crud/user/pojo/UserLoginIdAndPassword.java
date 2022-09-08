package com.example.spring_crud.user.pojo;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Deprecated
@Builder
@Value
public class UserLoginIdAndPassword {
    @NonNull @NotNull
    @Getter
    private final String loginId;

    @NonNull @NotNull
    @Getter
    private final String password;
}
