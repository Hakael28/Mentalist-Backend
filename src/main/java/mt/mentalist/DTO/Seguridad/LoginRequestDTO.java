package mt.mentalist.DTO.Seguridad;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String usuario;

    @NotBlank
    private String contrasena;
}
