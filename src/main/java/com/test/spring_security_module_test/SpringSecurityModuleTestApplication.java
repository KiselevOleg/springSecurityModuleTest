/**
 * Copyright 2023 Kiselev Oleg
 */
package com.test.spring_security_module_test;

import com.test.spring_security_module_test.util.ResetDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kiselev Oleg
 */
@SpringBootApplication
@SuppressWarnings({"PMD.UseUtilityClass", "PMD.HideUtilityClassConstructor"})
public class SpringSecurityModuleTestApplication {
    public static void main(final String[] args) {
        ResetDatabase.restActive = false;
        SpringApplication.run(SpringSecurityModuleTestApplication.class, args);
    }
}
