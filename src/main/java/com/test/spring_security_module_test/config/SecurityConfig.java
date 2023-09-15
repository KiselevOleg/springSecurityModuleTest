package com.test.spring_security_module_test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
            //.csrf(AbstractHttpConfigurer::disable)
            //.csrf(csrf -> csrf
            //    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            //)
            .rememberMe(session -> session
                .key("$2a$10$VeSgeeOswluWMy1/j/B9M.4OYgF.iFiTuLNI6KomFz45W5XFDZhYW")
                .tokenValiditySeconds(-1)
                .rememberMeCookieName("Uyr6583hHFu9hwuer")
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                //.requestMatchers(HttpMethod.GET, "/api/**").hasAuthority(PermissionName.DEVELOPER_READ)
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/auth/login").permitAll()
                .loginProcessingUrl("/auth/login")
                .failureUrl("/auth/login")
                .usernameParameter("username")
                .passwordParameter("userpassword")
                .defaultSuccessUrl("/auth/success")
            )
            .logout(form -> form
                    .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/auth/login")
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
