package mt.mentalist.controlador.Seguridad;


import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.configuracion.Seguridad.JwtUtil;
import mt.mentalist.servicio.Basicos.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class AuthControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?>

    login(@RequestParam String usuario, @RequestParam String contraseña) {
        UsuarioDTO encontrado = usuarioServicio.listarUsuarios().stream()
                .filter(u -> u.getUsuario().equals(usuario) &&
                        passwordEncoder.matches(contraseña, u.getContraseña()))
                .findFirst()
                .orElse(null);
        if (encontrado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales invalidas");
        }
        String token = jwtUtil.generarToken(encontrado.getUsuario(), encontrado.getRol().name(), encontrado.getIdUsuario());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
