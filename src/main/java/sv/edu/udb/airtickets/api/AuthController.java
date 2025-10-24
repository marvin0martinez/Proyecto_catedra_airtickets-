package sv.edu.udb.airtickets.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.dto.LoginRequest;
import sv.edu.udb.airtickets.security.JwtService;
import sv.edu.udb.airtickets.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;
    private final JwtService jwt;

    public AuthController(AuthService auth, JwtService jwt) {
        this.auth = auth;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return auth.authenticate(req.getEmail(), req.getPassword())
                .map(u -> {
                    var roles = u.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
                    Map<String,Object> claims = new HashMap<>();
                    claims.put("roles", roles);
                    String token = jwt.generate(u.getEmail(), claims);
                    Map<String,Object> body = new HashMap<>();
                    body.put("accessToken", token);
                    Map<String,Object> user = new HashMap<>();
                    user.put("email", u.getEmail());
                    user.put("fullName", u.getFullName());
                    user.put("roles", roles);
                    body.put("user", user);
                    return ResponseEntity.ok(body);
                })
                .orElse(ResponseEntity.status(401).body(Map.of("message","Invalid credentials")));
    }
}
