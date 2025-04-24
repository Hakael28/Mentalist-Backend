package mt.mentalist.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ruta_atencion")
public class RutaAtencion {
   
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     
   @Column(name="id_ruta_atencion", nullable = false) 
   @NotNull
   private Integer idRutaAtencion;
   
   @Column(length = 200 ) @Size(max = 200)
   private String descripcion;
   
   @OneToMany(mappedBy = "rutaAtencion", cascade = CascadeType.ALL)
   @ToString.Exclude
   @Builder.Default
   private List <Caso>casos =new ArrayList<>();
}
