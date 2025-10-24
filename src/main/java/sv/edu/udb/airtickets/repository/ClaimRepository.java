package sv.edu.udb.airtickets.repository;
import org.springframework.data.jpa.repository.JpaRepository; import sv.edu.udb.airtickets.domain.Claim;
public interface ClaimRepository extends JpaRepository<Claim, Long> {}