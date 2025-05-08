package mt.mentalist.DTO.DTOBasics;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RutaAtencionDTO {

    private Integer idRutaAtencion;

    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;
}
