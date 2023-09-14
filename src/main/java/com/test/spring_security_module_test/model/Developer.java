package com.test.spring_security_module_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.*;

/**
 * @author Kiselev Oleg
 */
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Developer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id") private Long id;
    @Column(name = "fist_name", length = 50, nullable = false, unique = false) private String firstName;
    @Column(name = "second_name", length = 50, nullable = false, unique = false) private String secondName;
}
