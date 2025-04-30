package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HistoriaClinicaDTO {

    private Integer idHistorialClinica;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El ID del caso es obligatorio")
    private Integer idCaso;

    @NotBlank(message = "La descripción de la historia clínica es obligatoria")
    @Size(max = 1000, message = "La descripción no debe superar los 1000 caracteres")
    private String descripcionHistoria;
}
