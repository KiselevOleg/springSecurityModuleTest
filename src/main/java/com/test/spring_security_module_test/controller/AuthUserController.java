package com.test.spring_security_module_test.controller;

import com.test.spring_security_module_test.service.security_database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiselev Oleg
 */
@RestController
@RequestMapping("/authUser")
public class AuthUserController {
    private final UserService userService;

    @Autowired
    AuthUserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/addUser")
    public String addUser(@RequestParam final String name, @RequestParam final String password) {
        try {
            userService.addUnregisteredUser(name, password);
        }
        catch(Exception ignored) { }
        return "index";
    }
}
