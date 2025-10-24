package sv.edu.udb.airtickets.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.Airline;
import sv.edu.udb.airtickets.repository.AirlineRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/airlines")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "admin-airline-controller")
public class AdminAirlineController {

    private final AirlineRepository repo;

    public AdminAirlineController(AirlineRepository repo) {
        this.repo = repo;
    }

    public record AirlineDto(@NotBlank String code, @NotBlank String name) {}

    @GetMapping
    public List<Airline> all() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Airline one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public ResponseEntity<Airline> create(@Valid @RequestBody AirlineDto dto) {
        var a = new Airline();
        a.setCode(dto.code());
        a.setName(dto.name());
        var saved = repo.save(a);
        return ResponseEntity.created(URI.create("/api/admin/airlines/" + saved.getId())).body(saved);
    }

    @PutMapping("{id}")
    public Airline update(@PathVariable Long id, @Valid @RequestBody AirlineDto dto) {
        var a = repo.findById(id).orElseThrow();
        a.setCode(dto.code());
        a.setName(dto.name());
        return repo.save(a);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}