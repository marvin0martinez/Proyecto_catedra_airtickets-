package sv.edu.udb.airtickets.domain;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Claim { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne private User user; @ManyToOne private Reservation reservation;
  private String type; @Column(length=1000) private String description; private String status; private LocalDateTime createdAt; }