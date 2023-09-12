package com.test.spring_security_module_test.controller;

import com.test.spring_security_module_test.model.Developer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody final Developer developer) {
        if (developers.stream().noneMatch(e -> e.getId().equals(developer.getId()))) {
            developers.add(developer);
            return ResponseEntity.status(HttpStatus.OK).body("success\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there already exists used id\n");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        if (developers.removeIf(developer -> developer.getId().equals(id))) {
            return ResponseEntity.status(HttpStatus.OK).body("success\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there does not exist used id\n");
        }
    }

}
