package com.test.spring_security_module_test.repository;

import com.test.spring_security_module_test.model.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kiselev Oleg
 */
@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
}
