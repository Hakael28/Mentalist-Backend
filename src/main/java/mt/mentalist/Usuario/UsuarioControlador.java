package mt.mentalist.Usuario;

import mt.mentalist.Global.Validacion.Actualizar;
import mt.mentalist.Global.Validacion.Crear;
import mt.mentalist.Global.exception.RecursoNoEncontradoExcepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mentalist-web/basicos")
public class UsuarioControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Obtener todos los usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Usuarios obtenidos:");
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
        return usuarios;
    }

    // Guardar un nuevo usuario (validación con grupo Crear)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> agregarUsuario(
            @Validated(Crear.class) @RequestBody UsuarioDTO dto) {

        logger.info("Usuario a agregar: " + dto);
        UsuarioDTO usuarioGuardado = this.usuarioServicio.guardarUsuario(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idUsuario}")
                .buildAndExpand(usuarioGuardado.getIdUsuario())
                .toUri();
        return ResponseEntity.created(ubicacion).body(usuarioGuardado);
    }

    // Obtener usuario por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable int idUsuario) {
        UsuarioDTO usuario = this.usuarioServicio.buscarUsuarioId(idUsuario);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontró el ID: " + idUsuario);
        }
    }

    // Eliminar usuario por ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/usuarios/{idUsuario}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable int idUsuario) {
        usuarioServicio.eliminarUsuario(idUsuario);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Actualizar información del usuario (rol médico) - validación con grupo Actualizar
    @PreAuthorize("hasRole('MEDICO')")
    @PutMapping("/usuarios/{idUsuario}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable int idUsuario,
            @Validated(Actualizar.class) @RequestBody UsuarioDTO dto) {

        UsuarioDTO actualizado = usuarioServicio.actualizarUsuarioRestricciones(idUsuario, dto);
        return ResponseEntity.ok(actualizado);
    }

    // Actualizar credenciales del usuario (rol administrador) - validación con grupo Actualizar
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/administrador/{idUsuario}/credenciales")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioAdmin(
            @PathVariable int idUsuario,
            @Validated(Actualizar.class) @RequestBody UsuarioDTO dto) {

        UsuarioDTO actualizado = usuarioServicio.actualizarUsuarioAdmin(idUsuario, dto);
        return ResponseEntity.ok(actualizado);
    }
}
