package mt.mentalist.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "historia_clinica")
public class HistoriaClinica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historia_clinica", nullable = false)
    private Integer idHistorialClinica;

    @ManyToOne
    @JoinColumn(name = "id_paciente_H", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_caso_H", nullable = false)
    @NotNull
    private Caso caso;

    @Column(name = "descripcion_historia")
    private String descripcionHistoria;

}
