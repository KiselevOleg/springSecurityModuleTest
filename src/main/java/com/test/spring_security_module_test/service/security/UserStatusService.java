package com.test.spring_security_module_test.service.security;

import com.test.spring_security_module_test.model.security.UserStatus;
import com.test.spring_security_module_test.repository.security.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kiselev Oleg
 */
@Service
public class UserStatusService {
    private final UserStatusRepository repository;

    @Autowired
    UserStatusService(final UserStatusRepository repository) {
        this.repository = repository;
    }

    public Optional<List<UserStatus>> getAllStatuses() {
        final List<UserStatus> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return Optional.of(list);
    }
}
