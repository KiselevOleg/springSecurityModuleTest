/**
 * Copyright 2023 Kiselev Oleg
 */
package com.test.spring_security_module_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kiselev Oleg
 */
@SpringBootApplication
@SuppressWarnings({"PMD.UseUtilityClass", "PMD.HideUtilityClassConstructor"})
public class SpringSecurityModuleTestApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SpringSecurityModuleTestApplication.class, args);
    }
}
