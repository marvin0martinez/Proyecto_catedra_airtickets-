package sv.edu.udb.airtickets.domain;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Flight flight; @ManyToOne(optional=false) private Passenger passenger;
  @ManyToOne private User user; private String locator; private String serviceClass; private String status;
  private double amount; private LocalDateTime createdAt; }