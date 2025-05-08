package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.servicio.Basicos.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8084/mentalist-web
@RequestMapping("mentalist-web")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioControlador {


    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Controlador pafra utilizar el metodo de listar usuarios
    //http://localhost:8084/mentalist-web/usuarios
    @GetMapping("/usuarios")
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos:");
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }

    // Controlador para utilizar el metodo de guardar usuarios
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> agregarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        logger.info("Usuario a agregar" + dto);
        UsuarioDTO usuarioGuardado = this.usuarioServicio.guardarUsuario(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idUsuario}")
                .buildAndExpand(usuarioGuardado.getIdUsuario())
                .toUri();
        return ResponseEntity.created(ubicacion).body(usuarioGuardado);
    }

    // Controlador para utilizar el metodo de buscar usuario por id
    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(
            @PathVariable int idUsuario) {
        UsuarioDTO usuario = this.usuarioServicio.buscarUsuarioId(idUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + idUsuario);
        }
    }

    // Controlador para utilizar el metodo de elimninar usuarios
    @DeleteMapping("/usuarios/{idUsuario}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarUsuario(@PathVariable int idUsuario) {
        usuarioServicio.eliminarUsuario(idUsuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de los usuarios con restriccion referente a rol
    @PutMapping("/usuarios/{idUsuario}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
           @PathVariable int idUsuario,
           @Valid @RequestBody UsuarioDTO dto){
        UsuarioDTO actulizado = usuarioServicio.actualizarUsuarioRestricciones(idUsuario, dto);
        return ResponseEntity.ok(actulizado);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de los usuarios
    @PutMapping("/administrador/{idUsuario}/credenciales")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioAdmin(
            @PathVariable int idUsuario,
            @Valid @RequestBody UsuarioDTO  dto) {
        UsuarioDTO actualizado = usuarioServicio.actulizarUsuarioAdmin(idUsuario, dto);
        return ResponseEntity.ok(actualizado);

    }
}