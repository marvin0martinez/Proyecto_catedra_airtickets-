package sv.edu.udb.airtickets.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.Passenger;
import sv.edu.udb.airtickets.repository.PassengerRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/passengers")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "admin-passenger-controller")
public class AdminPassengerController {

    private final PassengerRepository repo;

    public AdminPassengerController(PassengerRepository repo) { this.repo = repo; }

    public record PassengerDto(
            @NotBlank String firstName,
            @NotBlank String lastName,
            @NotBlank String documentId,
            @Email @NotBlank String email,
            @NotBlank String phone
    ) {}

    @GetMapping
    public List<Passenger> all(){ return repo.findAll(); }

    @GetMapping("{id}")
    public Passenger one(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody PassengerDto dto){
        var p = Passenger.builder()
                .firstName(dto.firstName()).lastName(dto.lastName())
                .documentId(dto.documentId()).email(dto.email()).phone(dto.phone())
                .build();
        var saved = repo.save(p);
        return ResponseEntity.created(URI.create("/api/admin/passengers/"+saved.getId())).body(saved);
    }

    @PutMapping("{id}")
    public Passenger update(@PathVariable Long id, @Valid @RequestBody PassengerDto dto){
        var p = repo.findById(id).orElseThrow();
        p.setFirstName(dto.firstName());
        p.setLastName(dto.lastName());
        p.setDocumentId(dto.documentId());
        p.setEmail(dto.email());
        p.setPhone(dto.phone());
        return repo.save(p);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){ repo.deleteById(id); }
}