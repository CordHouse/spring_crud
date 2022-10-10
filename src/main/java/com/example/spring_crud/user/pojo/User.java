package com.example.spring_crud.user.pojo;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @apiNote
 * 유저 도메인 영역을 책임지는 객체
 * 객체에 대한 분리는 도메인 영역의 확장에 따라 고려할 필요가 있는데 본 객체에 얼마나의 필드를 포함할 것이고 분리할 것인지가 관건(현재 Deprecated된 {@link UserLoginIdAndPassword }UserLoginIdAndPassword처럼 분리할 것인지를 의미)
 */
@Builder

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Getter
    private final UserId userId;

    private Username username;

    private Password password;

    private Name name;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }

    @Override
    public String getUsername() {
        return username.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    @Value
    public static class UserId {
        private Long value;
    }

    @Value
    public static class Username {
        private String value;
    }

    @Value
    public static class Password {
        private String value;
    }

    @Value
    public static class Name {
        private String value;
    }
}
