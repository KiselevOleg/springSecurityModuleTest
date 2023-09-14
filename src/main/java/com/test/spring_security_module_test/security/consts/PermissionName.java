package com.test.spring_security_module_test.security.consts;

import lombok.Getter;

/**
 * @author Kiselev Oleg
 */
@Getter
public enum PermissionName {
    DEVELOPER_READ("developer_read"),
    DEVELOPER_WRITE("developer_write");

    private final String name;

    PermissionName(final String name) {
        this.name = name;
    }
}
