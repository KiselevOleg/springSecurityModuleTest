package com.test.spring_security_module_test.dto.security;

/**
 * @author Kiselev Oleg
 * @param name - имя
 * @param password - пароль
 */
public record AuthRestRequestDTO(String name, String password) { }
