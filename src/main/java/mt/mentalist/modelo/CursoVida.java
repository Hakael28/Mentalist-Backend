
package mt.mentalist.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import mt.mentalist.modelo.Enum.Etapa;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "curso_vida")
public class CursoVida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso_vida", nullable = false )
   @NotNull
    private Integer idCursoVida;
    
    @Enumerated(EnumType.STRING)
    private Etapa etapa;
    
    @OneToMany(mappedBy = "cursoVida", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso>casos=new ArrayList<>();
}
    
    
