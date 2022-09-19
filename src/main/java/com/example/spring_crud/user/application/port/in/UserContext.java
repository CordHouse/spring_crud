package com.example.spring_crud.user.application.port.in;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @apiNote SecurityContextHolder를 사용할 수 있게 하는 인터페이스
 */
@Service
public interface UserContext {
    User getCurrentUser();

    void setCurrentUser(User user);
}
