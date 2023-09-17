package com.test.spring_security_module_test.controller.security.rest;

import com.test.spring_security_module_test.dto.security.AuthRestRequestDTO;
import com.test.spring_security_module_test.model.security.User;
import com.test.spring_security_module_test.repository.security.UserRepository;
import com.test.spring_security_module_test.security.rest.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings("PMD.PreserveStackTrace")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController(final AuthenticationManager authenticationManager,
                              final UserRepository userRepository,
                              final JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(final @RequestBody AuthRestRequestDTO requestDTO) {
        try {
            final String name = requestDTO.name();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, requestDTO.password()));
            final User user = userRepository.findByName(requestDTO.name())
                .orElseThrow(() -> new UsernameNotFoundException("User has been not found"));
            final String token = jwtTokenProvider.createToken(requestDTO.name(), user.getRole().getName());
            final Map<Object, Object> response = new HashMap<>();
            response.put("name", requestDTO.name());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            //return new ResponseEntity<>("Invalid name/password combination", HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(final HttpServletRequest request, final HttpServletResponse response) {
        final SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
