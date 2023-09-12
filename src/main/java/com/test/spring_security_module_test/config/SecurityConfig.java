package com.test.spring_security_module_test.config;

import com.test.spring_security_module_test.model.Permision;
import com.test.spring_security_module_test.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings({"PMD.MultipleStringLiterals", "PMD.AvoidDuplicateLiterals"})
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/").permitAll()
            //.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
            //.requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole(Role.ADMIN.name())
            //.requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole(Role.ADMIN.name())
            .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority(Permision.DEVELOPERS_READ.getPermision())
            .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(Permision.DEVELOPERS_WRITE.getPermision())
            .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(Permision.DEVELOPERS_WRITE.getPermision())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            //.password(passwordEncoder().encode("admin"))
            .password("admin")
            //.roles(Role.ADMIN.name())
            .authorities(Role.ADMIN.getAuthorities())
            .build();
        final UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            //.password(passwordEncoder().encode("admin"))
            .password("user")
            //.roles(Role.USER.name())
            .authorities(Role.USER.getAuthorities())
            .build();
        final UserDetails user1 = User.withDefaultPasswordEncoder()
            .username("user1")
            //.password(passwordEncoder().encode("admin"))
            .password("user1")
            .build();
        return new InMemoryUserDetailsManager(admin, user, user1);
    }

    /*private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }*/
}
