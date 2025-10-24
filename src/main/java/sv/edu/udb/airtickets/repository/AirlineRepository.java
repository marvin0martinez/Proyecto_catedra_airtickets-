package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Airline; import java.util.Optional;
public interface AirlineRepository extends JpaRepository<Airline, Long> { Optional<Airline> findByCode(String code); }