package sv.edu.udb.airtickets.api;
import org.springframework.http.ResponseEntity; import org.springframework.validation.annotation.Validated; import org.springframework.web.bind.annotation.*; import sv.edu.udb.airtickets.dto.FlightSearchRequest; import sv.edu.udb.airtickets.service.FlightService;
@RestController @RequestMapping("/api/flights")
public class FlightController {
  private final FlightService service; public FlightController(FlightService s){ this.service=s; }
  @PostMapping("/search") public ResponseEntity<?> search(@RequestBody @Validated FlightSearchRequest req){
    return ResponseEntity.ok(service.search(req.originIata(), req.destinationIata(), req.date())); }
}