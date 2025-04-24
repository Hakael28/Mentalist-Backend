package mt.mentalist.modelo;

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
    @NotNull
    private Integer idAreaOcurrencia;

    @Column(length = 100, nullable = false )
   @NotNull
    @Size(max = 100)
    private String nombre;

    @OneToMany(mappedBy = "areaOcurrencia", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Caso> casos=new ArrayList<>();
}
