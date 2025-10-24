package sv.edu.udb.airtickets.api;
import org.springframework.web.bind.annotation.*; import org.springframework.http.ResponseEntity; import org.springframework.security.access.prepost.PreAuthorize;
import sv.edu.udb.airtickets.repository.*;
@RestController @RequestMapping("/api/admin")
public class AdminStatsController {
  private final ReservationRepository resRepo; private final PaymentRepository payRepo; private final FlightRepository flightRepo;
  public AdminStatsController(ReservationRepository r, PaymentRepository p, FlightRepository f){ this.resRepo=r; this.payRepo=p; this.flightRepo=f; }
  @GetMapping("/stats/summary") @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
  public ResponseEntity<?> summary(){ return ResponseEntity.ok(java.util.Map.of("reservations", resRepo.count(), "payments", payRepo.count(), "flights", flightRepo.count())); }
}