package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Reservation; import sv.edu.udb.airtickets.domain.User; import java.util.*;
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByLocator(String locator); List<Reservation> findByUser(User user);
}