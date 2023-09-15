package com.test.spring_security_module_test.controller.security;

import com.test.spring_security_module_test.consts.ResetDatabaseConfig;
import com.test.spring_security_module_test.util.ResetDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kiselev Oleg
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final ResetDatabase resetDatabase;

    @Autowired
    AuthController(final ResetDatabase resetDatabase) {
        this.resetDatabase = resetDatabase;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        if (ResetDatabaseConfig.RESET_DATABASE_IS_ACTIVE) {
            resetDatabase.reset();
        }
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }
}
