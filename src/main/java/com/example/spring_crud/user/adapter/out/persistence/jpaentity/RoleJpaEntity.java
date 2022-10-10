package com.example.spring_crud.user.adapter.out.persistence.jpaentity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="role")
public class RoleJpaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, unique=true)
    private String rolename;

    @OneToMany(mappedBy = "id")
    private List<UserRole> userRoles;

}
