package mt.mentalist.DTO.Seguridad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String rol;
    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String usuario;
}

