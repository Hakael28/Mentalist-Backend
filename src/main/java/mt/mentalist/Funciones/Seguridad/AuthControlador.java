package mt.mentalist.Funciones.Seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    @Autowired
    private AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authServicio.autenticarUsuario(request);
        return ResponseEntity.ok(response);
    }
}
