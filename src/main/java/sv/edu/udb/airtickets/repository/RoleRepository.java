package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Role; import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role, Long> { Optional<Role> findByName(String name); }