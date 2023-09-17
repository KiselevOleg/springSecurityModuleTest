package com.test.spring_security_module_test.config;

import com.test.spring_security_module_test.security.UserDetailsServiceDatabase;
import com.test.spring_security_module_test.security.rest.TokenConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings({"PMD.MultipleStringLiterals", "PMD.AvoidDuplicateLiterals"})
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(
    prePostEnabled = true, //pre/post annotations
    securedEnabled = false, //@Secured
    jsr250Enabled = false) //@RoleAllowed
@ComponentScan("com.test.spring_security_module_test")
public class SecurityConfig {
    private final UserDetailsServiceDatabase userDetailsServiceDatabase;
    private final TokenConfigurer tokenConfigurer;

    @Autowired
    public SecurityConfig(final UserDetailsServiceDatabase userDetailsServiceDatabase,
                          final TokenConfigurer tokenConfigurer) {
        this.userDetailsServiceDatabase = userDetailsServiceDatabase;
        this.tokenConfigurer = tokenConfigurer;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/auth/login").permitAll()
                //.requestMatchers(HttpMethod.GET, "/api/**").hasAuthority(PermissionName.DEVELOPER_READ)
                //.anyRequest().authenticated()
                .anyRequest().permitAll()
            )
            .apply(tokenConfigurer);
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    @Primary
    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(
        final AuthenticationManagerBuilder authenticationManagerBuilder
    ) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceDatabase).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }
}
