package com.test.spring_security_module_test.repository.security_database;

import com.test.spring_security_module_test.model.security_database.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kiselev Oleg
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
