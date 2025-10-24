// src/main/java/sv/edu/udb/airtickets/domain/Role.java
package sv.edu.udb.airtickets.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role") // <--- 'role' (singular, como en tu BD)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name", nullable = false, unique = true)
  private String name;
}
