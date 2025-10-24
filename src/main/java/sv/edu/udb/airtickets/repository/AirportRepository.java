package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Airport; import java.util.Optional;
public interface AirportRepository extends JpaRepository<Airport, Long> { Optional<Airport> findByIata(String iata); }