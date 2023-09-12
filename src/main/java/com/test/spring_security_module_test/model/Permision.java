package com.test.spring_security_module_test.model;

/**
 * @author Kiselev Oleg
 */
public enum Permision {
    DEVELOPERS_READ("com.test.spring_security_module_test.model.developers_read_permission"),
    DEVELOPERS_WRITE("com.test.spring_security_module_test.model.developers_write_permission");

    private final String permision;

    Permision(final String permision) {
        this.permision = permision;
    }

    public String getPermision() {
        return permision;
    }
}
