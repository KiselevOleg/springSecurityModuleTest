package com.test.spring_security_module_test.security.consts;

/**
 * @author Kiselev Oleg
 */
public enum PermissionName {
    DEVELOPER_READ("DEVELOPER_READ"),
    DEVELOPER_WRITE("DEVELOPER_WRITE");

    private final String name;

    PermissionName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
