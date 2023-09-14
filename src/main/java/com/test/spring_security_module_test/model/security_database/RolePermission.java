package com.test.spring_security_module_test.model.security_database;

/**
 * @author Kiselev Oleg
 */
public class RolePermission { }

/*import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role_permission", schema = "public")
public class RolePermission {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @Column(name = "role") private Role role;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @Column(name = "permission") private Permission permission;
}*/
