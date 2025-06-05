package mt.mentalist.DTO.Seguridad;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank
    private String usuario;

    @NotBlank
    private String contrasena;
}
