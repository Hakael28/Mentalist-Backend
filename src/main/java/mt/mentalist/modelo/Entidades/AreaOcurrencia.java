package mt.mentalist.modelo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "area_ocurrencia")
public class AreaOcurrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_ocurrencia", nullable = false )
    private Integer idAreaOcurrencia;

    @Column(length = 255, nullable = false )
   @NotNull
    @Size(max = 255)
    private String nombre;

    @OneToMany(mappedBy = "areaOcurrencia", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso> casos=new ArrayList<>();
}
