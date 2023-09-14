package com.test.spring_security_module_test.service;

import com.test.spring_security_module_test.model.Developer;
import com.test.spring_security_module_test.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
/**
 * @author Kiselev Oleg
 */
@Service
public class DeveloperService {
    private final DeveloperRepository repository;

    @Autowired
    public DeveloperService(final DeveloperRepository repository) {
        this.repository = repository;
    }

    public Optional<Developer> findById(final Long id) {
        return repository.findById(id);
    }
    public List<Developer> findAll() {
        List<Developer> list = new LinkedList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Boolean add(Developer developer) {
        if (repository.findById(developer.getId()).isPresent()) {
            return false;
        }

        developer.setId(null);
        repository.save(developer);
        return true;
    }

    public Boolean deleteById(final Long id) {
        if (repository.findById(id).isEmpty()) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
