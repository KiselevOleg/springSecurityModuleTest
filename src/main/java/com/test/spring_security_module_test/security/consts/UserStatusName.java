package com.test.spring_security_module_test.security.consts;

import lombok.Getter;

/**
 * @author Kiselev Oleg
 */
@Getter
public enum UserStatusName {
    UNACTIVE("not active"),
    ACTIVE("active"),
    SUSPICIOUS("suspicious"),
    BLOCKED("blocked"),
    BANED("banned");

    private final String name;

    UserStatusName(final String name) {
        this.name = name;
    }
}
