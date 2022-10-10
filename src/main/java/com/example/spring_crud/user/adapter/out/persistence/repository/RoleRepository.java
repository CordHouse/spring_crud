package com.example.spring_crud.user.adapter.out.persistence.repository;

import com.example.spring_crud.user.adapter.out.persistence.jpaentity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleJpaEntity, Long> {
    boolean existsByRolename(String rolename);

    Optional<RoleJpaEntity> findByRolename(String rolename);
}
