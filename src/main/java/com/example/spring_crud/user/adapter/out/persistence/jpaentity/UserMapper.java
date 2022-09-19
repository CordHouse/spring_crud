package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import com.example.spring_crud.user.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @apiNote
 * {@link User}와 {@link UserJpaEntity }간에 매핑하는 객체
 */
@Component
public class UserMapper {
    public User mapToDomainUser(UserJpaEntity userJpaEntity) {
        return User.builder()
                .userId(new User.UserId(userJpaEntity.getId()))
                .username(new User.Username(userJpaEntity.getUsername()))
                .password(new User.Password(userJpaEntity.getPassword()))
                .build();
    }

    public UserJpaEntity mapToJpaEntityUser(User user) {
        return UserJpaEntity.builder()
                .id(((user.getUserId()==null?null:user.getUserId().getValue())))
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
