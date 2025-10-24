package sv.edu.udb.airtickets.dto;
import jakarta.validation.constraints.*;
public record CreatePassengerRequest(@NotBlank String firstName,@NotBlank String lastName,@NotBlank String documentId,@Email String email,@NotBlank String phone) {}