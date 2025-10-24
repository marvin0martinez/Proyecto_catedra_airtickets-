package sv.edu.udb.airtickets.domain;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Reservation reservation;
  private String method; private String status; private double amount; private LocalDateTime paidAt; }