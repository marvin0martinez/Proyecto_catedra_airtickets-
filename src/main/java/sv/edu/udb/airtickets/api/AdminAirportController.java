package sv.edu.udb.airtickets.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.Airport;
import sv.edu.udb.airtickets.repository.AirportRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/airports")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "admin-airport-controller")
public class AdminAirportController {

    private final AirportRepository repo;

    public AdminAirportController(AirportRepository repo) {
        this.repo = repo;
    }

    public record AirportDto(@NotBlank String iata, @NotBlank String city, @NotBlank String country) {}

    @GetMapping
    public List<Airport> all() { return repo.findAll(); }

    @GetMapping("{id}")
    public Airport one(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PostMapping
    public ResponseEntity<Airport> create(@Valid @RequestBody AirportDto dto) {
        var a = new Airport();
        a.setIata(dto.iata());
        a.setCity(dto.city());
        a.setCountry(dto.country());
        var saved = repo.save(a);
        return ResponseEntity.created(URI.create("/api/admin/airports/" + saved.getId())).body(saved);
    }

    @PutMapping("{id}")
    public Airport update(@PathVariable Long id, @Valid @RequestBody AirportDto dto) {
        var a = repo.findById(id).orElseThrow();
        a.setIata(dto.iata());
        a.setCity(dto.city());
        a.setCountry(dto.country());
        return repo.save(a);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}