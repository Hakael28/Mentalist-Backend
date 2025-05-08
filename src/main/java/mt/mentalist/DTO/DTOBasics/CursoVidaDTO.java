package mt.mentalist.DTO.DTOBasics;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import mt.mentalist.modelo.Enum.Etapa;

@Data
public class CursoVidaDTO {

    private Integer idCursoVida;

    @NotBlank(message = "La etapa del curso de vida es obligatorio")
    private Etapa etapa;
}
