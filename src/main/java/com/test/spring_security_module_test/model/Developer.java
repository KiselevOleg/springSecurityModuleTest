package com.test.spring_security_module_test.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Kiselev Oleg
 */
@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developer", schema = "public")
public class Developer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
    @Column(name = "fist_name", length = 50, nullable = false, unique = false) private String firstName;
    @Column(name = "second_name", length = 50, nullable = false, unique = false) private String secondName;
}
