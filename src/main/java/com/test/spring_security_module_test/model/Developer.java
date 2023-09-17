package com.test.spring_security_module_test.model;

import com.test.spring_security_module_test.dto.DeveloperDTO;
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

    public static Developer fromDTO(final DeveloperDTO developerDTO) {
        return  new Developer(null, developerDTO.firstName(), developerDTO.secondName());
    }
}
