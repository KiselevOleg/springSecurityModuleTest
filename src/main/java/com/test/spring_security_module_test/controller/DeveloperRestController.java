package com.test.spring_security_module_test.controller;

import com.test.spring_security_module_test.model.Developer;
import com.test.spring_security_module_test.consts.security.PermissionName;
import com.test.spring_security_module_test.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kiselev Oleg
 */
@RestController
@RequestMapping("/api/developers")
public class DeveloperRestController {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperRestController(final DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('" + PermissionName.DEVELOPER_READ + "')")
    public List<Developer> getAll() {
        return developerService.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + PermissionName.DEVELOPER_WRITE + "')")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        return developerService.findById(id)
            .map(e -> ResponseEntity.status(HttpStatus.OK).body((Object) e))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found this developer"));
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('" + PermissionName.DEVELOPER_WRITE + "')")
    public ResponseEntity<?> create(@RequestBody final Developer developer) {
        if (developerService.add(developer)) {
            return ResponseEntity.status(HttpStatus.OK).body("success\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there already exists used id\n");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + PermissionName.DEVELOPER_WRITE + "')")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        if (developerService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("success\n");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there does not exist used id\n");
        }
    }

}
