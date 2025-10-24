package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Flight; import sv.edu.udb.airtickets.domain.Airport; import java.time.LocalDateTime; import java.util.List;
public interface FlightRepository extends JpaRepository<Flight, Long> {
  List<Flight> findByOriginAndDestinationAndDepartureTimeBetween(Airport origin, Airport destination, LocalDateTime start, LocalDateTime end);
}