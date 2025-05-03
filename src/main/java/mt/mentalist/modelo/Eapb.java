
package mt.mentalist.modelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "eapb")
public class Eapb {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   @Column(name="id_eapb", nullable = false)
   @NotNull
   private Integer idEapb;
   
  @Column(length = 255)
  @Size(max = 255)
   private String nombre;
   
   @OneToMany(mappedBy = "eapb", cascade = CascadeType.ALL)
   @ToString.Exclude
   @Builder.Default
   @JsonIgnore
   private List<Caso>casos = new ArrayList<>();
   }
