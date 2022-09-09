package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @apiNote
 * 유저 객체를 {@link User}와 {@link UserJpaEntity }간에 매핑하는 객체
 */
@Component
public class UserMapper {
    public User mapToDomainUser(UserJpaEntity userJpaEntity) {
        return User.builder()
                .userId(new User.UserId(userJpaEntity.getId()))
                .loginId(new User.LoginId(userJpaEntity.getLoginId()))
                .loginPassword(new User.LoginPassword(userJpaEntity.getPassword()))
                .build();
    }

    public UserJpaEntity mapToJpaEntityUser(User user) {
        return UserJpaEntity.builder()
                .id(((user.getUserId()==null?null:user.getUserId().getValue())))
                .loginId(user.getLoginId().getValue())
                .password(user.getLoginPassword().getValue())
                .build();
    }
}
