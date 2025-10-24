package sv.edu.udb.airtickets.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.airtickets.domain.*;
import sv.edu.udb.airtickets.repository.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/flights")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "admin-flight-controller")
public class AdminFlightController {

    private final FlightRepository flights;
    private final AirlineRepository airlines;
    private final AirportRepository airports;

    public AdminFlightController(FlightRepository flights, AirlineRepository airlines, AirportRepository airports) {
        this.flights = flights;
        this.airlines = airlines;
        this.airports = airports;
    }

    public record FlightDto(
            @NotNull Long airlineId,
            @NotNull Long originId,
            @NotNull Long destinationId,
            @NotBlank String aircraftType,
            @NotNull @Future LocalDateTime departureTime,
            @NotNull @Future LocalDateTime arrivalTime,
            @Min(1) int capacity,
            @Min(0) double baseFare
    ) {}

    @GetMapping
    public List<Flight> all(){ return flights.findAll(); }

    @GetMapping("{id}")
    public Flight one(@PathVariable Long id){ return flights.findById(id).orElseThrow(); }

    @PostMapping
    public ResponseEntity<Flight> create(@Valid @RequestBody FlightDto dto){
        Airline airline = airlines.findById(dto.airlineId()).orElseThrow();
        Airport origin = airports.findById(dto.originId()).orElseThrow();
        Airport destination = airports.findById(dto.destinationId()).orElseThrow();

        var f = Flight.builder()
                .airline(airline)
                .origin(origin)
                .destination(destination)
                .aircraftType(dto.aircraftType())
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .capacity(dto.capacity())
                .booked(0)
                .baseFare(dto.baseFare())
                .build();
        var saved = flights.save(f);
        return ResponseEntity.created(URI.create("/api/admin/flights/"+saved.getId())).body(saved);
    }

    @PutMapping("{id}")
    public Flight update(@PathVariable Long id, @Valid @RequestBody FlightDto dto){
        var f = flights.findById(id).orElseThrow();
        f.setAirline(airlines.findById(dto.airlineId()).orElseThrow());
        f.setOrigin(airports.findById(dto.originId()).orElseThrow());
        f.setDestination(airports.findById(dto.destinationId()).orElseThrow());
        f.setAircraftType(dto.aircraftType());
        f.setDepartureTime(dto.departureTime());
        f.setArrivalTime(dto.arrivalTime());
        f.setCapacity(dto.capacity());
        f.setBaseFare(dto.baseFare());
        return flights.save(f);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){ flights.deleteById(id); }
}