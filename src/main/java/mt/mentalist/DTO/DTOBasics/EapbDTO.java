package mt.mentalist.DTO.DTOBasics;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EapbDTO {
    private Integer idEapb;
    @NotBlank(message = "El nombre del eapb es obligatorio")
    private String nombre;
}
