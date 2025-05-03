package mt.mentalist.modelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
   
   @Column(length = 255 )
   @Size(max = 255)
   private String descripcion;
   
   @OneToMany(mappedBy = "rutaAtencion", cascade = CascadeType.ALL)
   @ToString.Exclude
   @Builder.Default
   @JsonIgnore
   private List <Caso>casos =new ArrayList<>();
}
