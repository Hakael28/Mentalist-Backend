package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class AreaOcurrenciaDTO {

    private Integer idAreaOcurrencia;

    @NotBlank(message = "El nombre del area es obligatorio")
    private String nombre;

}
