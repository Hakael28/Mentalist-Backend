package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import mt.mentalist.modelo.Enum.Etapa;

@Data
public class CursoVidaDTO {

    @NotBlank(message = "La etapa del curso de vida es obligatorio")
    private Etapa etapa;
}
