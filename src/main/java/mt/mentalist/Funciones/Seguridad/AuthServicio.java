package mt.mentalist.Funciones.Seguridad;

import lombok.RequiredArgsConstructor;
import mt.mentalist.Usuario.Usuario;
import mt.mentalist.Usuario.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final EncriptacionServicio encriptacionServicio;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO autenticarUsuario(LoginRequestDTO request) {
        Usuario usuario = usuarioRepositorio.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        boolean contraseñaValida = encriptacionServicio.verificarContraseña(
                request.getContrasena(), usuario.getContrasena()
        );

        if (!contraseñaValida) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(
                usuario.getUsuario(),
                usuario.getRol().name(),
                usuario.getIdUsuario()

        );

        return new LoginResponseDTO(
                token,
                usuario.getRol().name(),
                usuario.getIdUsuario(),
                encriptacionServicio.desencriptarTexto(usuario.getNombre()),
                encriptacionServicio.desencriptarTexto(usuario.getTelefono()),
                encriptacionServicio.desencriptarTexto(usuario.getCorreo()),
                usuario.getUsuario()
        );    }
}