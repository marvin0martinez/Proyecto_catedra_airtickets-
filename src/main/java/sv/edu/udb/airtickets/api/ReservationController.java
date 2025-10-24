package sv.edu.udb.airtickets.api;
import org.springframework.http.ResponseEntity; import org.springframework.validation.annotation.Validated; import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.Passenger; import sv.edu.udb.airtickets.dto.*; import sv.edu.udb.airtickets.service.*;
@RestController @RequestMapping("/api")
public class ReservationController {
  private final ReservationService resService; private final PaymentService payService;
  public ReservationController(ReservationService r, PaymentService p){ this.resService=r; this.payService=p; }
  @PostMapping("/passengers") public ResponseEntity<?> createPassenger(@RequestBody @Validated CreatePassengerRequest req){
    var p = Passenger.builder().firstName(req.firstName()).lastName(req.lastName()).documentId(req.documentId()).email(req.email()).phone(req.phone()).build();
    return ResponseEntity.ok(resService.createPassenger(p)); }
  @PostMapping("/reservations") public ResponseEntity<?> createReservation(@RequestBody @Validated CreateReservationRequest req){
    return ResponseEntity.ok(resService.createReservation(req.flightId(), req.passengerId(), req.serviceClass())); }
  @GetMapping("/reservations/{id}") public ResponseEntity<?> get(@PathVariable Long id){ return ResponseEntity.ok(resService.get(id)); }
  @GetMapping("/reservations/locator/{code}") public ResponseEntity<?> getByLocator(@PathVariable String code){ return ResponseEntity.ok(resService.getByLocator(code)); }
  @PatchMapping("/reservations/{id}/cancel") public ResponseEntity<?> cancel(@PathVariable Long id){ return ResponseEntity.ok(resService.cancel(id)); }
  @PostMapping("/payments") public ResponseEntity<?> pay(@RequestBody @Validated PaymentRequest req){ return ResponseEntity.ok(payService.pay(req.reservationId(), req.method(), req.amount())); }
}