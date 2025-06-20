package mt.mentalist.DTO.Basicas;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EapbDTO {
    private Integer idEapb;
    @NotBlank(message = "El nombre del eapb es obligatorio")
    private String nombre;
}
