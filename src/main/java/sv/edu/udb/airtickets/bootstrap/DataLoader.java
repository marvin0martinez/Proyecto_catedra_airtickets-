package sv.edu.udb.airtickets.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sv.edu.udb.airtickets.domain.Role;
import sv.edu.udb.airtickets.domain.User;
import sv.edu.udb.airtickets.repository.RoleRepository;
import sv.edu.udb.airtickets.repository.UserRepository;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(RoleRepository roles, UserRepository users) {
        return args -> {
            var enc = new BCryptPasswordEncoder();
            var adminRole = roles.findByName("ADMIN").orElseGet(() -> roles.save(Role.builder().name("ADMIN").build()));
            var userRole  = roles.findByName("USER").orElseGet(() -> roles.save(Role.builder().name("USER").build()));

            users.findByEmail("admin@demo.local").orElseGet(() -> users.save(User.builder()
                    .email("admin@demo.local").fullName("Admin Demo")
                    .password(enc.encode("Admin123*")).enabled(true)
                    .roles(Set.of(adminRole, userRole)).build()));

            users.findByEmail("user1@demo.local").orElseGet(() -> users.save(User.builder()
                    .email("user1@demo.local").fullName("Usuario Uno")
                    .password(enc.encode("User123*")).enabled(true)
                    .roles(Set.of(userRole)).build()));

            users.findByEmail("user2@demo.local").orElseGet(() -> users.save(User.builder()
                    .email("user2@demo.local").fullName("Usuario Dos")
                    .password(enc.encode("User123*")).enabled(true)
                    .roles(Set.of(userRole)).build()));
        };
    }
}
