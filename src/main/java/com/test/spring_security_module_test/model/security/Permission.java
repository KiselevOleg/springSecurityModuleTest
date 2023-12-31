package com.test.spring_security_module_test.model.security;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Kiselev Oleg
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_permission", schema = "public")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;

    @Column(name = "name", length = 30, nullable = false, unique = true) private String name;

    //@ManyToMany(fetch=FetchType.LAZY, mappedBy = "permissions")
    //private Set<Role> roles;
}
