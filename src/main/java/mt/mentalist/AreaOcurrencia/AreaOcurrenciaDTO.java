package mt.mentalist.AreaOcurrencia;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AreaOcurrenciaDTO {

    private Integer idAreaOcurrencia;

    @NotBlank(message = "El nombre del area es obligatorio")
    private String nombre;

}
