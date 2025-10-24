package sv.edu.udb.airtickets.domain;
import jakarta.persistence.*; import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Passenger { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String firstName; private String lastName; private String documentId; private String email; private String phone; }