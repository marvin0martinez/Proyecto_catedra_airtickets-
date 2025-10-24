package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> {}