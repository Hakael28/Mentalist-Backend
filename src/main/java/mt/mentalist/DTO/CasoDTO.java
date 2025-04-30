package mt.mentalist.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import mt.mentalist.modelo.*;

import java.time.LocalDate;

@Data
public class CasoDTO {

    private Integer idCaso;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    private Integer idAreaOcurrencia;

    private Integer idRutaAtencion;

    private Integer idEapb;

    private Integer idCursoVida;

    private Integer idDiagnosticoEspecifico;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "La fecha de notificación es obligatoria")
    @PastOrPresent(message = "La fecha de notificación no puede ser futura")
    private LocalDate fechaNotificacion;

    @Min(value = 1, message = "La semana epidemiológica debe estar entre 1 y 53")
    @Max(value = 53, message = "La semana epidemiológica debe estar entre 1 y 53")
    private int semanaEpidemiologica;

    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;

    @PastOrPresent(message = "La fecha de revisión de la historia no puede ser futura")
    private LocalDate fechaRevisionHistoria;

    @Size(max = 255, message = "La remisión a ruta de salud no debe superar los 255 caracteres")
    private String remisionRutaSalud;
}
