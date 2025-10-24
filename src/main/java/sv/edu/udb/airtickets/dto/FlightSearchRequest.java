package sv.edu.udb.airtickets.dto;
import java.time.LocalDate; import jakarta.validation.constraints.*;
public record FlightSearchRequest(@NotBlank String originIata,@NotBlank String destinationIata,@NotNull LocalDate date) {}