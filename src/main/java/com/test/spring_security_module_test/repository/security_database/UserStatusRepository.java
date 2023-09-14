package com.test.spring_security_module_test.repository.security_database;

import com.test.spring_security_module_test.model.security_database.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Repository
public interface UserStatusRepository extends CrudRepository<UserStatus, Long> {
    Optional<UserStatus> findByName(String name);
}
