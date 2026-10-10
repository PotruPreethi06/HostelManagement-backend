package com.example.hostelmanagement.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY =
            "MyHostelManagementSystemSecretKey2026ForJWTAuthentication123456";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            var key = Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8)
            );

            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            System.out.println("=================================");
            System.out.println("JWT EMAIL: " + email);
            System.out.println("JWT ROLE: " + role);

            SimpleGrantedAuthority authority =
                    new SimpleGrantedAuthority("ROLE_" + role);

            System.out.println(
                    "AUTHORITY: " + authority.getAuthority()
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(authority)
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            System.out.println(
                    "AUTHENTICATED: " +
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .isAuthenticated()
            );

            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "Invalid JWT token: " + e.getMessage()
            );
        }

        filterChain.doFilter(request, response);
    }
}