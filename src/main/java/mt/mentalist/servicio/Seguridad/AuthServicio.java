package mt.mentalist.servicio.Seguridad;

import lombok.RequiredArgsConstructor;
import mt.mentalist.DTO.Seguridad.LoginRequestDTO;
import mt.mentalist.DTO.Seguridad.LoginResponseDTO;
import mt.mentalist.configuracion.Seguridad.JwtUtil;
import mt.mentalist.modelo.Entidades.Usuario;
import mt.mentalist.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final EncriptacionServicio encriptacionServicio;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO autenticarUsuario(LoginRequestDTO request) {
        Usuario usuario = usuarioRepositorio.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encriptacionServicio.verificarContraseña(request.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(
                usuario.getUsuario(),
                usuario.getRol().name(),
                usuario.getIdUsuario()
        );

        return new LoginResponseDTO(token, usuario.getRol().name(), usuario.getIdUsuario());
    }
}
