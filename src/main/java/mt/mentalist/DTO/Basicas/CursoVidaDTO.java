package mt.mentalist.DTO.Basicas;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import mt.mentalist.modelo.Enum.Etapa;

@Data
public class CursoVidaDTO {

    private Integer idCursoVida;

    @NotNull
    private Etapa etapa;
}
