package sv.edu.udb.airtickets.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.Reservation;
import sv.edu.udb.airtickets.repository.ReservationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "admin-reservation-controller")
public class AdminReservationCrudController {

    private final ReservationRepository repo;

    public AdminReservationCrudController(ReservationRepository repo){ this.repo = repo; }

    @GetMapping public List<Reservation> all(){ return repo.findAll(); }

    @GetMapping("{id}") public Reservation one(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

    // DELETE duro: solo para fines docentes (la app normal usa cancelación lógica)
    @DeleteMapping("{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}