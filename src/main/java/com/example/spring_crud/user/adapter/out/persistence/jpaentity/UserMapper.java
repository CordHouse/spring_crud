package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import com.example.spring_crud.user.pojo.CustomGrantedAuthority;
import com.example.spring_crud.user.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
                .name(new User.Name(userJpaEntity.getName()))
                .authorities(userJpaEntity.getUserRoles().stream().map(role-> new CustomGrantedAuthority(role.getRoleJpaEntity().getRolename())).toList())
                .build();
    }

    public UserJpaEntity mapToJpaEntityUser(User user) {
        return UserJpaEntity.builder()
                .id(((user.getUserId()==null?null:user.getUserId().getValue())))
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }
}
