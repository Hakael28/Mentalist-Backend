package mt.mentalist.controlador.Seguridad;


import mt.mentalist.DTO.Fuctions.Seguridad.LoginRequestDTO;
import mt.mentalist.DTO.Fuctions.Seguridad.LoginResponseDTO;
import mt.mentalist.servicio.Funciones.Seguridad.AuthServicio;
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
