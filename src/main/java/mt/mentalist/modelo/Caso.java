package mt.mentalist.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "caso")
public class Caso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caso", nullable = false)
    @NotNull
    private Integer idCaso;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @NotNull
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_area_ocurrencia", nullable = false)
    @NotNull
    private AreaOcurrencia areaOcurrencia;

    @ManyToOne
    @JoinColumn(name = "id_ruta_atencion", nullable = false)
    @NotNull
    private RutaAtencion rutaAtencion;

    @ManyToOne
    @JoinColumn(name = "id_eapb", nullable = false)
    @NotNull
    private Eapb eapb;

    @ManyToOne
    @JoinColumn(name = "id_curso_vida", nullable = false)
    @NotNull
    private CursoVida cursoVida;

    @ManyToOne
    @JoinColumn(name = "id_diagnostico_especifico", nullable = false)
    @NotNull
    private DiagnosticoEspecifico diagnostico;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_notificacion")
    private LocalDate fechaNotificacion;

    @Column(name = "semana_epidemiologica")
    private int semanaEpidemiologica;

    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_revision_historia")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaRevisionHistoria;

    @Column(name = "remision_ruta_salud")
    private String remisionRutaSalud;

    @OneToMany(mappedBy = "caso", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<HistoriaClinica> historiasClinicas = new ArrayList<>();

}
