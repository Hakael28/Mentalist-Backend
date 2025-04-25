package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RutaAtencionDTO {

    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;
}
