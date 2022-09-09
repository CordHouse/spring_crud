package com.example.spring_crud.user.adapter.out.persistence.repository;

import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.example.spring_crud.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @apiNote
 * 유저에 대한 RDBMS Repository. {@link UserJpaEntity}를 영속한다.
 */
@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
