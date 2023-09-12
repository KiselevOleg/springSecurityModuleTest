package com.test.spring_security_module_test.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kiselev Oleg
 */
public enum Role {
    USER(Set.of(Permision.DEVELOPERS_READ)),
    ADMIN(Set.of(Permision.DEVELOPERS_READ, Permision.DEVELOPERS_WRITE));

    private final Set<Permision> permisions;

    Role(final Set<Permision> permisions) {
        this.permisions = permisions;
    }

    public Set<Permision> getPermisions() {
        return permisions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermisions().stream()
            .map(e -> new SimpleGrantedAuthority(e.getPermision()))
            .collect(Collectors.toSet());
    }
}
