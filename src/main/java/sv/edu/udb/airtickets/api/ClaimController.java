package sv.edu.udb.airtickets.api;
import org.springframework.http.ResponseEntity; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import sv.edu.udb.airtickets.service.ClaimService; import java.util.Map;
@RestController @RequestMapping("/api/claims")
public class ClaimController {
  private final ClaimService service; public ClaimController(ClaimService s){ this.service=s; }
  @PostMapping public ResponseEntity<?> create(@RequestBody Map<String,Object> body){
    Long reservationId = body.get("reservationId")==null?null:Long.valueOf(body.get("reservationId").toString());
    String type = String.valueOf(body.get("type")); String description = String.valueOf(body.get("description"));
    return ResponseEntity.ok(service.create(reservationId, type, description)); }
  @GetMapping("/me") public ResponseEntity<?> mine(){ return ResponseEntity.ok(service.myClaims()); }
  @PatchMapping("/{id}/status") @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
  public ResponseEntity<?> status(@PathVariable Long id, @RequestBody Map<String,String> body){
    return ResponseEntity.ok(service.updateStatus(id, body.getOrDefault("status","OPEN"))); }
}