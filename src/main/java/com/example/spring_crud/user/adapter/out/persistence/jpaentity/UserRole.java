package com.example.spring_crud.user.adapter.out.persistence.jpaentity;


import com.example.spring_crud.user.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER")
    private UserJpaEntity userJpaEntity;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private RoleJpaEntity roleJpaEntity;
}
