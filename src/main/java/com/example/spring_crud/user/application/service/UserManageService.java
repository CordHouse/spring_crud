package com.example.spring_crud.user.application.service;

import com.example.spring_crud.user.application.port.in.UserLoginUseCase;
import com.example.spring_crud.user.application.port.in.UserRegisterUseCase;
import com.example.spring_crud.user.application.port.out.InsertAccountPort;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.User;
import com.example.spring_crud.user.pojo.dto.UserLoginRequestDto;
import com.example.spring_crud.user.pojo.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    @Override
    public void userRegister(UserRegisterRequestDto userRegisterRequestDto) {
        //lock

        //load
        if(!loadAccountPort.existsUserByLoginId(new User.LoginId(userRegisterRequestDto.getLoginId()))){
            //valid

            //process

            //update
            insertAccountPort.insertUser(User.builder()
                    .loginId(new User.LoginId(userRegisterRequestDto.getLoginId()))
                    .loginPassword((new User.LoginPassword(userRegisterRequestDto.getPassword())))
                    .build());
        }

        //release
    }

    @Override
    public void userLogin(UserLoginRequestDto userLoginRequestDto) {

    }
}
