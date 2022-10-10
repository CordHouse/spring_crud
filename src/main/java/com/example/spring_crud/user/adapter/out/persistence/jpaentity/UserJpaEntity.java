package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import com.example.spring_crud.user.pojo.CustomGrantedAuthority;
import com.example.spring_crud.user.pojo.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @apiNote 
 * RDBMS 유저 엔티티
 * 도메인 영역으로 {@link User}가 있음
 * // TODO: 2022-09-08 칼럼 추가 필요 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@Data
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String password;

    @OneToMany(mappedBy = "id")
    private List<UserRole> userRoles;

    @NotNull
    @Column
    private String name;
}
