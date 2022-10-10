package com.example.spring_crud.user.application.service;

import com.example.spring_crud.configuration.security.provider.JwtTokenProvider;
import com.example.spring_crud.user.application.port.in.UserLoginUseCase;
import com.example.spring_crud.user.application.port.in.UserRegisterUseCase;
import com.example.spring_crud.user.application.port.out.CustomUserDetailsManager;
import com.example.spring_crud.user.application.port.out.InsertAccountPort;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.CustomGrantedAuthority;
import com.example.spring_crud.user.pojo.User;
import com.example.spring_crud.user.pojo.dto.TokenResponseDto;
import com.example.spring_crud.user.pojo.dto.UserLoginRequestDto;
import com.example.spring_crud.user.pojo.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @apiNote
 * {@link User} 관리 영역. 도메인 관리, 비즈니스 로직 수행(대부분은 도메인에서 수행하고 남는 부분)을 여기서 한다.
 * // TODO: 2022-09-09 회원가입 유스케이스 강화(예외처리 등), 로그인 구현(jwt, spring security 등), 회원정보 수정 구현
 */
@RequiredArgsConstructor
@Service
public class UserManageService implements UserRegisterUseCase, UserLoginUseCase {
    final private LoadAccountPort loadAccountPort;
    final private InsertAccountPort insertAccountPort;

    final private CustomUserDetailsManager userDetailsManager;

    final private PasswordEncoder passwordEncoder;

    final private JwtTokenProvider jwtTokenProvider;

    @Override
    public void userRegister(UserRegisterRequestDto userRegisterRequestDto) {
        //lock

        //load
        if(!loadAccountPort.existsUserByUsername(new User.Username(userRegisterRequestDto.getUsername()))){
            //valid

            //process

            //update
            insertAccountPort.insertUser(User.builder()
                    .username(new User.Username(userRegisterRequestDto.getUsername()))
                    .password((new User.Password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))))
                    .name(new User.Name(userRegisterRequestDto.getName()))
                    .authorities(Arrays.asList(new CustomGrantedAuthority("ROLE_USER")))
                    .build());
        }

        //release
    }

    @Override
    public TokenResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {
        UserDetails user = userDetailsManager.loadUserByUsername(userLoginRequestDto.getUsername());
        return TokenResponseDto.builder()
                .token(jwtTokenProvider.createToken(user.getUsername(), user.getAuthorities()))
                .build();
    }
}
