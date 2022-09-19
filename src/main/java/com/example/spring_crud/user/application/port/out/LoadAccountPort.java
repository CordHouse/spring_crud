package com.example.spring_crud.user.application.port.out;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @apiNote
 * 계정에 대한 출력 포트(읽기 전용)
 *
 */
@Service
public interface LoadAccountPort {
    User loadUser(User.UserId userId);
    User loadUser(User.Username username);

    boolean existsUserByUsername(User.Username username);
}
