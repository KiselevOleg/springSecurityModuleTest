package com.test.spring_security_module_test.model.security_database;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Kiselev Oleg
 */
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_status", schema = "public")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;

    @Column(name = "name", length = 20, nullable = false, unique = true) private String name;
}
