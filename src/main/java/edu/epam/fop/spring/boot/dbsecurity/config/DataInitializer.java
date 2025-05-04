package edu.epam.fop.spring.boot.dbsecurity.config;

import edu.epam.fop.spring.boot.entity.Role;
import edu.epam.fop.spring.boot.entity.User;
import edu.epam.fop.spring.boot.repository.RoleRepository;
import edu.epam.fop.spring.boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "USER")));
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));

        userRepository.findByUsername("user")
                .orElseGet(() -> {
                    User u = new User();
                    u.setUsername("user");
                    u.setPassword(passwordEncoder.encode("password"));
                    u.setRoles(Set.of(userRole));
                    return userRepository.save(u);
                });

        userRepository.findByUsername("admin")
                .orElseGet(() -> {
                    User a = new User();
                    a.setUsername("admin");
                    a.setPassword(passwordEncoder.encode("admin"));
                    a.setRoles(Set.of(adminRole));
                    return userRepository.save(a);
                });
    }
}
