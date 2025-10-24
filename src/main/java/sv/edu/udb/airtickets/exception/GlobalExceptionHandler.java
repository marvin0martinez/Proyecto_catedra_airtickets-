package sv.edu.udb.airtickets.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*;
import java.util.*; @RestControllerAdvice public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<?> v(MethodArgumentNotValidException ex){
    Map<String,String> errors=new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(e->errors.put(e.getField(), e.getDefaultMessage()));
    return ResponseEntity.badRequest().body(Map.of("message","Validation failed","errors",errors)); }
  @ExceptionHandler(RuntimeException.class) public ResponseEntity<?> r(RuntimeException ex){
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("message", ex.getMessage())); }
}