package com.test.spring_security_module_test.security.rest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings("PMD.PreserveStackTrace")
@Component
public class TokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public TokenFilter(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilterInternal(final HttpServletRequest servletRequest,
                         final HttpServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final String token = jwtTokenProvider.resolveToken(servletRequest);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                final Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (final TokenAuthException e) {
            SecurityContextHolder.clearContext();
            (servletResponse).sendError(e.getHttpStatus().value());
            throw new TokenAuthException("JWT token is expired or invalid");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
