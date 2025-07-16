package mt.mentalist.CursoVida;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoVidaDTO {

    private Integer idCursoVida;

    @NotNull
    private Etapa etapa;
}
