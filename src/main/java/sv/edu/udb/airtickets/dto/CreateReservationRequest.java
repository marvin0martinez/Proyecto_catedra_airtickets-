package sv.edu.udb.airtickets.dto;
import jakarta.validation.constraints.*;
public record CreateReservationRequest(@NotNull Long flightId,@NotNull Long passengerId,@NotBlank String serviceClass) {}