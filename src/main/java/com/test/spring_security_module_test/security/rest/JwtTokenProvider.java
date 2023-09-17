package com.test.spring_security_module_test.security.rest;

import com.test.spring_security_module_test.security.UserDetailsServiceDatabase;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings("PMD.PreserveStackTrace")
@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Value("${security.jwt.secretKey}")
    private String secretKey;
    @Value("${security.jwt.lifetimeBySeconds}")
    private Integer lifetimeBySeconds;
    @Value("${security.jwt.header}")
    private String authenticationHeader;

    @Autowired
    public JwtTokenProvider(final UserDetailsServiceDatabase userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(final String name, final String role) {
        return Jwts.builder()
            .claim("role", role)
            .setSubject(name)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + lifetimeBySeconds * 1000))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public Boolean validateToken(final String token) {
        try {
            return !Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenAuthException("JWT token is invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public String getNameFromToken(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(final String token) {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(getNameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(final HttpServletRequest request) {
        return request.getHeader(authenticationHeader);
    }
}
