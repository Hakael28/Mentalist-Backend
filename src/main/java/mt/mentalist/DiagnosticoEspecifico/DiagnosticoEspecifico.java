package mt.mentalist.DiagnosticoEspecifico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mt.mentalist.Caso.Caso;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "diagnostico_especifico")
public class DiagnosticoEspecifico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico_especifico", nullable = false)
    private Integer idDiagnosticoEspecifico;

    @Column(name = "tipo_diagnostico")
    @Enumerated(EnumType.STRING)
    private TipoDiagnostico tipoDiagnostico;

    @Column(name = "codigo_cie", length = 255)
    @Size(max = 255)
    private String codigoCie;

    @Column(name = "observaciones_medicas")
    private String observacionesMedicas;

    @Column(name = "fecha_diagnostico")
    private LocalDate fechaDiagnostico;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso> casos = new ArrayList<>();
}
