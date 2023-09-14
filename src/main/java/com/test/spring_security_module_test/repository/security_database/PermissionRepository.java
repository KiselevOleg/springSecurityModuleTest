package com.test.spring_security_module_test.repository.security_database;

import com.test.spring_security_module_test.model.security_database.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kiselev Oleg
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
