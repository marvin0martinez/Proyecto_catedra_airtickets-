package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Passenger;
public interface PassengerRepository extends JpaRepository<Passenger, Long> {}