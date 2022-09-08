package com.example.spring_crud.user.application.port.out;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @apiNote
 * 계정에 대한 출력 포트(읽기 전용)
 */
@Component
public interface LoadAccountPort {
    User roadUser(User.UserId userId);
    User roadUser(User.LoginId userLoginId);

    boolean existsUserByLoginId(User.LoginId userLoginId);
}
