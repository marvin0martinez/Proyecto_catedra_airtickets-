package sv.edu.udb.airtickets.dto;
import jakarta.validation.constraints.*;
public record PaymentRequest(@NotNull Long reservationId,@NotBlank String method,@Positive double amount) {}