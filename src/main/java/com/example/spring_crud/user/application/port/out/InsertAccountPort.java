package com.example.spring_crud.user.application.port.out;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @apiNote
 * 계정에 대한 출력 포트.
 * 삽입, 수정, 삭제를 담당.
 */
@Service
public interface InsertAccountPort {
    void insertUser(User user);
    void updateUser(User newUserInfo);
    void deleteUser(User.UserId userId);
}
