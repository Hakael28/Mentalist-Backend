package mt.mentalist.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mt.mentalist.modelo.Enum.TipoDiagnostico;


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
    @NotNull
    private Integer idDiagnosticoEspecifico;

    @Column(name = "tipo_diagnostico", length = 100)
    @Enumerated(EnumType.STRING)
    private TipoDiagnostico tipodiagnostico;

    @Column(name = "codigo_cie", length = 100)
    @Size(max = 100)
    private String codigoCie;

    @Column(name = "observaciones_medicas", length = 100)
    private String observacionesMedicas;

    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.DATE)
    private LocalDate Fechadiagnostico;

    @OneToMany(mappedBy = "diagnostico", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso> casos = new ArrayList<>();
}
