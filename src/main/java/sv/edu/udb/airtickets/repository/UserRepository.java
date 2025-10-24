// src/main/java/sv/edu/udb/airtickets/repository/UserRepository.java
package sv.edu.udb.airtickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.airtickets.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
}
