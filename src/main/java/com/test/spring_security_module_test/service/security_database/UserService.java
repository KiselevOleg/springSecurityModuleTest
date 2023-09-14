package com.test.spring_security_module_test.service.security_database;

import com.test.spring_security_module_test.model.security_database.User;
import com.test.spring_security_module_test.repository.security_database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Service("userService")
public class UserService {
    private final UserRepository repository;

    @Autowired
    UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public Optional<List<User>> getAllUsers() {
        final List<User> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return Optional.of(list);
    }

    public Optional<User> getUserByName(final String name) {
        return repository.findByName(name);
    }
}
