// src/main/java/sv/edu/udb/airtickets/service/AuthService.java
package sv.edu.udb.airtickets.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sv.edu.udb.airtickets.domain.User;
import sv.edu.udb.airtickets.repository.UserRepository;
import java.util.Optional;

@Service
public class AuthService {
  private final UserRepository users;
  private final PasswordEncoder encoder;

  public AuthService(UserRepository users, PasswordEncoder encoder) {
    this.users = users;
    this.encoder = encoder;
  }

  public Optional<User> authenticate(String email, String rawPassword) {
    return users.findByEmailIgnoreCase(email.trim())
            .filter(User::isEnabled)
            .filter(u -> encoder.matches(rawPassword, u.getPassword()));
  }
}
