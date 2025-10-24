package sv.edu.udb.airtickets.api;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api")
public class HealthController { @GetMapping("/health") public ResponseEntity<?> health(){ return ResponseEntity.ok(Map.of("status","OK","service","airtickets")); } }