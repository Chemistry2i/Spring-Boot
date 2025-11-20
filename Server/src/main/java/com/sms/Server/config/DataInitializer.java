package com.sms.Server.config;

import com.sms.Server.entity.Role;
import com.sms.Server.entity.User;
import com.sms.Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Create default superadmin
            User superadmin = new User();
            superadmin.setUsername("superadmin");
            superadmin.setEmail("superadmin@school.com");
            superadmin.setPassword(passwordEncoder.encode("password"));
            superadmin.setRoles(Set.of(Role.SUPERADMIN));
            superadmin.setEnabled(true);
            userRepository.save(superadmin);

            // Create default admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@school.com");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRoles(Set.of(Role.ADMIN));
            admin.setEnabled(true);
            userRepository.save(admin);

            System.out.println("Default users created:");
            System.out.println("Superadmin: superadmin / password");
            System.out.println("Admin: admin / password");
        }
    }
}