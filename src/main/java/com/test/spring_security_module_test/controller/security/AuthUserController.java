package com.test.spring_security_module_test.controller.security;

import com.test.spring_security_module_test.consts.security.PermissionName;
import com.test.spring_security_module_test.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings("PMD.SystemPrintln")
@RestController
@RequestMapping("/authUser")
public class AuthUserController {
    private final UserService userService;

    @Autowired
    AuthUserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    @PreAuthorize("hasAuthority('" + PermissionName.DEVELOPER_WRITE + "')")
    public String addUser(@RequestParam final String name, @RequestParam final String password) {
        try {
            userService.addUnregisteredUser(name, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }
}
