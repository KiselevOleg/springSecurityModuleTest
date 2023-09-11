package com.test.spring_security_module_test.controller;

import com.test.spring_security_module_test.model.Developer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Kiselev Oleg
 */
@RestController
@RequestMapping("/api/developers")
public class DeveloperRestController {
    private final List<Developer> developers = Stream.of(
        new Developer(1L, "fname1", "sname1"),
        new Developer(2L, "fname2", "sname2"),
        new Developer(3L, "fname3", "sname3")
    ).toList();

    @GetMapping("")
    public List<Developer> getAll() {
        return developers;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        return developers.stream()
            .filter(e -> e.getId().equals(id))
            .map(e -> ResponseEntity.status(HttpStatus.OK).body((Object) e))
            .findFirst()
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found this developer"));
    }
}
