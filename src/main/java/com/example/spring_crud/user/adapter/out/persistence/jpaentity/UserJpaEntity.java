package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import com.example.spring_crud.user.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * @apiNote 
 * RDBMS 유저 엔티티
 * 도메인 영역으로 {@link User}가 있음
 * // TODO: 2022-09-08 칼럼 추가 필요 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Data
public class UserJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String loginId;

    @NotNull
    @Column
    private String password;
}
