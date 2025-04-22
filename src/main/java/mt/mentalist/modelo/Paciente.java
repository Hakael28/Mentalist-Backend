package mt.mentalist.modelo;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import mt.mentalist.modelo.Enum.Genero;
import mt.mentalist.modelo.Enum.TipoDocumento;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "id_paciente", nullable = false)
    @NotNull
    private Integer idPaciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "edad")
    private int edad;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(name = "nacionalidad", length = 100)
    @Size(max = 100)
    private String nacionalidad;

    @Column(name = "telefono", length = 11)
    @Size(max = 11)
    private String telefono;

    @Column(length = 100)
    @Email
    private String correo;

    @Size(max = 150)
    private String direccion;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Caso> casos = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<HistoriaClinica> historiaClinicas = new ArrayList<>();

    @PrePersist
    @PreUpdate
    private void calcularEdad() {
        if (this.fechaNacimiento != null) {
            try {
                this.edad = Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
            }catch (Exception e){
                this.edad =0;
                System.err.println("Error al calcular la edad: " + e.getMessage());
            }
        }else{
            this.edad= 0;
        }
    }
}

