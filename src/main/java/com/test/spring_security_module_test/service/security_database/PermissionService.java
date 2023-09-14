package com.test.spring_security_module_test.service.security_database;

import com.test.spring_security_module_test.model.security_database.Permission;
import com.test.spring_security_module_test.repository.security_database.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Service("permissionService")
public class PermissionService {
    private final PermissionRepository repository;

    @Autowired
    PermissionService(final PermissionRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Permission>> getAllPermissions() {
        final List<Permission> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return Optional.of(list);
    }
}
