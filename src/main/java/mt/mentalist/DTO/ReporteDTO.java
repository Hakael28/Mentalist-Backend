package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mt.mentalist.modelo.Enum.TipoReporte;

import java.time.LocalDate;

@Data
public class ReporteDTO {

    private Integer idReporte;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "El tipo de reporte es obligatorio")
    private TipoReporte tipoReporte;

    @NotBlank(message = "La descripción del reporte es obligatoria")
    @Size(max = 1000, message = "La descripción no debe superar los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha del reporte es obligatoria")
    @PastOrPresent(message = "La fecha del reporte no puede ser futura")
    private LocalDate fecha;
}

