package com.test.spring_security_module_test.security.consts;

import lombok.Getter;

/**
 * @author Kiselev Oleg
 */
@Getter
public enum RoleName {
    ADMIN("admin"),
    USER("user"),
    VIEWER("viewer");

    private final String name;

    RoleName(final String name) {
        this.name = name;
    }

}
