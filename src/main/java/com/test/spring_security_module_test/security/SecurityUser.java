package com.test.spring_security_module_test.security;

import com.test.spring_security_module_test.model.security_database.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Kiselev Oleg
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final String name;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final Boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromDatabaseUser(final User user) {
        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            user.getStatus().getHasGrantToSingIn(),
            user.getStatus().getHasGrantToSingIn(),
            user.getStatus().getHasGrantToSingIn(),
            user.getStatus().getHasGrantToSingIn(),
            user.getRole().getPermissions().stream().map(e -> new SimpleGrantedAuthority(e.getName())).toList()
        );
    }
}
