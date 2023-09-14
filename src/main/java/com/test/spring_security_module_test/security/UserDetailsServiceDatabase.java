package com.test.spring_security_module_test.security;

import com.test.spring_security_module_test.model.security_database.User;
import com.test.spring_security_module_test.repository.security_database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Kiselev Oleg
 */
@Service
public class UserDetailsServiceDatabase implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceDatabase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return SecurityUser.fromDatabaseUser(user);
    }
}
