package mt.mentalist.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mt.mentalist.Global.Validacion.Crear;
import mt.mentalist.Global.Validacion.Actualizar;

@Data
public class UsuarioDTO {

    private Integer idUsuario;

    @NotBlank(message = "El nombre del profesional es obligatorio", groups = {Crear.class, Actualizar.class})
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres", groups = {Crear.class, Actualizar.class})
    private String nombre;

    @NotBlank(message = "El nombre de usuario es obligatorio", groups = {Crear.class})
    @Size(max = 100, message = "El nombre de usuario no debe superar los 100 caracteres", groups = {Crear.class})
    private String usuario;

    @NotNull(message = "El rol del usuario es obligatorio", groups = {Crear.class, Actualizar.class})
    private Rol rol;

    @NotBlank(message = "La contraseña es obligatoria", groups = {Crear.class})
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres", groups = {Crear.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Solo escritura
    private String contrasena;

    @NotBlank(message = "El correo del usuario es obligatorio", groups = {Crear.class, Actualizar.class})
    private String correo;

    @NotNull(message = "El teléfono del usuario es obligatorio", groups = {Crear.class, Actualizar.class})
    private String telefono;
}
