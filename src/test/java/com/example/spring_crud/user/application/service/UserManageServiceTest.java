package com.example.spring_crud.user.application.service;

import com.example.spring_crud.configuration.security.provider.JwtTokenProvider;
import com.example.spring_crud.user.application.port.out.CustomUserDetailsManager;
import com.example.spring_crud.user.application.port.out.InsertAccountPort;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.User;
import com.example.spring_crud.user.pojo.dto.TokenResponseDto;
import com.example.spring_crud.user.pojo.dto.UserLoginRequestDto;
import com.example.spring_crud.user.pojo.dto.UserRegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * @apiNote
 * {@link UserManageService}의 단위 테스트 
 * // TODO: 2022-09-26  여러 경로 추가
 */
@ExtendWith(MockitoExtension.class)
class UserManageServiceTest {
    @InjectMocks
    private UserManageService userManageService;

    @Mock
    private LoadAccountPort loadAccountPort;

    @Mock
    private InsertAccountPort insertAccountPort;

    @Mock
    private CustomUserDetailsManager userDetailsManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void userRegister() {
        //GIVEN
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .username("aaaaaaa2")
                .password("aaaaaaaaA1@")
                .build();

        given(loadAccountPort.existsUserByUsername(ArgumentMatchers.any(User.Username.class)))
                .willReturn(false);

        //WHEN
        userManageService.userRegister(userRegisterRequestDto);
        //THEN
    }

    @Test
    void userLogin() {
        //GIVEN
        UserLoginRequestDto userLoginRequestDto = UserLoginRequestDto
                .builder()
                .username("aaaaaaa2")
                .password("aaaaaaaaA1@")
                .build();

        given(userDetailsManager.loadUserByUsername(anyString()))
                .willReturn(User.builder()
                        .userId(new User.UserId(1L))
                        .username(new User.Username("aaaaaaa2"))
                        .password(new User.Password("aaaaaaaaA1@"))
                        .authorities(new ArrayList<>()).build());
        given(jwtTokenProvider.createToken(anyString(), anyList()))
                .willReturn("");
        //WHEN
        TokenResponseDto tokenResponseDto = userManageService.userLogin(userLoginRequestDto);

        //THEN
        assertInstanceOf(TokenResponseDto.class, tokenResponseDto);
    }
}