package com.example.spring_crud.user.adapter.out.persistence;

import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserMapper;
import com.example.spring_crud.user.adapter.out.persistence.repository.UserRepository;
import com.example.spring_crud.user.application.port.out.InsertAccountPort;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @apiNote
 * {@link User}의 out.port에 대한 어댑터
 */
@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements LoadAccountPort, InsertAccountPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override

    public User roadUser(User.UserId userId) {
        return userMapper.mapToDomainUser(userRepository.findById(userId.getValue()).orElse(null));
    }

    @Deprecated
    @Override
    public User roadUser(User.LoginId userLoginId) {
        UserJpaEntity userJpaEntity = userRepository.findByLoginId(userLoginId.getValue()).orElseThrow(NoSuchElementException::new);

        return userMapper.mapToDomainUser(userJpaEntity);
    }

    @Override
    public boolean existsUserByLoginId(User.LoginId userLoginId) {
        return userRepository.existsByLoginId(userLoginId.getValue());
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
}
