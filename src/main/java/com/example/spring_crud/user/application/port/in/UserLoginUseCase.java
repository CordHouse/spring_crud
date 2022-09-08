package com.example.spring_crud.user.application.port.in;

import com.example.spring_crud.user.pojo.User;
import com.example.spring_crud.user.pojo.dto.UserLoginRequestDto;
import org.springframework.stereotype.Service;

/**
 * @apiNote
 * {@link User}중 일반 권한을 가진 객체에 대한 로그인 유스케이스
 */
@Service
public interface UserLoginUseCase {
    void userLogin(UserLoginRequestDto userLoginRequestDto);
}
