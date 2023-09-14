package com.test.spring_security_module_test.service.security_database;

import com.test.spring_security_module_test.config.SecurityConfig;
import com.test.spring_security_module_test.model.security_database.User;
import com.test.spring_security_module_test.repository.security_database.RoleRepository;
import com.test.spring_security_module_test.repository.security_database.UserRepository;
import com.test.spring_security_module_test.repository.security_database.UserStatusRepository;
import com.test.spring_security_module_test.security.consts.RoleName;
import com.test.spring_security_module_test.security.consts.UserStatusName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Service
public class UserService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;

    @Autowired
    UserService(final UserRepository repository,
                final RoleRepository roleRepository,
                final UserStatusRepository userStatusRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
    }

    public Optional<List<User>> getAllUsers() {
        final List<User> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return Optional.of(list);
    }

    public Optional<User> getUserByName(final String name) {
        return repository.findByName(name);
    }

    public void addUnregisteredUser(final String name, final String password) {
        repository.save(
            new User(null,
                name,
                SecurityConfig.passwordEncoder().encode(password),
                roleRepository.findByName(RoleName.VIEWER.getName()).orElseThrow(),
                userStatusRepository.findByName(UserStatusName.UNACTIVE.getName()).orElseThrow()
            )
        );
    }
}
