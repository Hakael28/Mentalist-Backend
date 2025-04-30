package mt.mentalist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EapbDTO {
    private Integer idEapb;
    @NotBlank(message = "El nombre del eapb es obligatorio")
    private String nombre;
}
