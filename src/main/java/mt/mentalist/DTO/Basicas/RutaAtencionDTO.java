package mt.mentalist.DTO.Basicas;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RutaAtencionDTO {

    private Integer idRutaAtencion;

    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;
}
