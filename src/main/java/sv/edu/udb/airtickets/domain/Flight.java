package sv.edu.udb.airtickets.domain;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Flight { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Airline airline;
  @ManyToOne(optional=false) private Airport origin;
  @ManyToOne(optional=false) private Airport destination;
  private String aircraftType; private int capacity; private double baseFare;
  private LocalDateTime departureTime; private LocalDateTime arrivalTime;
  @Builder.Default private int booked=0; }