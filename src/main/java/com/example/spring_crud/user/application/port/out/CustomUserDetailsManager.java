package com.example.spring_crud.user.application.port.out;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @apiNote UserDetails 조회를 위한 인터페이스
 */
@Service
public interface CustomUserDetailsManager extends UserDetailsManager {
}
