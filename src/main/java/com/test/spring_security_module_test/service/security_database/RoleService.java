package com.test.spring_security_module_test.service.security_database;

import com.test.spring_security_module_test.model.security_database.Role;
import com.test.spring_security_module_test.repository.security_database.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Service
public class RoleService {
    private final RoleRepository repository;

    @Autowired
    RoleService(final RoleRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Role>> getAllRoles() {
        final List<Role> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return Optional.of(list);
    }
}
