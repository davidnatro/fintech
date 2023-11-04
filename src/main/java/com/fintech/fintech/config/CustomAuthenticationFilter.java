package com.fintech.fintech.config;

import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.repository.hibernate.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String[] header = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ");

        if (header.length != 2 || !header[0].equals("Basic") || StringUtils.isBlank(header[1])) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            filterChain.doFilter(request, response);
            return;
        }

        byte[] base64Credentials = Base64.getDecoder().decode(header[1]);
        String utf8Credentials = new String(base64Credentials, StandardCharsets.UTF_8);
        String[] credentials = utf8Credentials.split(":");
        if (credentials.length != 2) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            filterChain.doFilter(request, response);
            return;
        }

        String username = credentials[0];
        String password = credentials[1];

        Optional<User> optionalUser = repository.findByUsername(username);

        if (optionalUser.isEmpty() || !passwordEncoder.matches(password,
                                                               optionalUser.get().getPassword())) {
            log.warn("bad credentials for user '{}'", username);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            filterChain.doFilter(request, response);
            return;
        }

        User user = optionalUser.get();

        Authentication token= new UsernamePasswordAuthenticationToken(user, password, user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .toList());

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}
