package com.test.spring_security_module_test.model;

import lombok.*;

/**
 * @author Kiselev Oleg
 */
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstName;
    private String secondName;
}
