package com.example.spring_crud.user.adapter.out.persistence;

import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserMapper;
import com.example.spring_crud.user.adapter.out.persistence.repository.UserRepository;
import com.example.spring_crud.user.application.port.out.CustomUserDetailsManager;
import com.example.spring_crud.user.application.port.out.InsertAccountPort;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.*;

/**
 * @apiNote
 * {@link User}의 out.port에 대한 어댑터
 * // TODO: 2022/09/17 port들과  CustomUserDetailsManager 중 책임이 겹치는 것을 고려하고 구현
 */
@RequiredArgsConstructor
@Service
public class UserPersistenceAdapter implements LoadAccountPort, InsertAccountPort, CustomUserDetailsManager {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User loadUser(User.UserId userId) {
        return userMapper.mapToDomainUser(userRepository.findById(userId.getValue()).orElse(null));
    }

    @Deprecated
    @Transactional(readOnly = true)
    public User loadUser(User.Username username) {
        UserJpaEntity userJpaEntity = userRepository.findByUsername(username.getValue()).orElseThrow(NoSuchElementException::new);

        return userMapper.mapToDomainUser(userJpaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByUsername(User.Username username) {
        return userRepository.existsByUsername(username.getValue());
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(userMapper.mapToJpaEntityUser(user));
    }

    @Override
    public void updateUser(User newUserInfo) {
        userRepository.save(userMapper.mapToJpaEntityUser(newUserInfo));
    }

    @Override
    public void deleteUser(User.UserId userId) {
        userRepository.deleteById(userId.getValue());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserJpaEntity> user =  userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Invalid username/password"));

        User domainUser = userMapper.mapToDomainUser(user.get());
        // TODO: 2022/09/14  authorities 생성 구현체 구현
        //Collection<? extends GrantedAuthority> authorities =
        //Set<GrantedAuthority> grantedAuthorities = new HashSet();
        //for (Role role: user.getRoles()) grantedAuthorities.add(new SimpleGrantedAuthority(role.getName());
        Collection<? extends GrantedAuthority> authorities = new LinkedList<>();
        return new org.springframework.security.core.userdetails.User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                authorities);
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @RolesAllowed("hasRole('USER')")
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean userExists(String username) {
        return false;
    }
}
