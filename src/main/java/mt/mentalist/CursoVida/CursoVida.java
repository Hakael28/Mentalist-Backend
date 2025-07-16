
package mt.mentalist.CursoVida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import mt.mentalist.Caso.Caso;

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
    private Integer idCursoVida;
    
    @Enumerated(EnumType.STRING)
    private Etapa etapa;
    
    @OneToMany(mappedBy = "cursoVida", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso>casos=new ArrayList<>();
}
    
    
