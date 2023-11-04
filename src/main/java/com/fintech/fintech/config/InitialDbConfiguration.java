package com.fintech.fintech.config;

import com.fintech.fintech.data.entity.Role;
import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.repository.hibernate.RoleRepository;
import com.fintech.fintech.data.repository.hibernate.UserRepository;
import com.fintech.fintech.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitialDbConfiguration {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.getRoles()
                    .add(roleRepository.findByName("USER").orElseThrow(NotFoundException::new));
            userRepository.save(user);
        }

        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("password"));
            user.getRoles()
                    .add(roleRepository.findByName("ADMIN").orElseThrow(NotFoundException::new));
            userRepository.save(user);
        }
    }
}
