package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.UsuarioDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.servicio.UsuarioServicio;
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
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = this.usuarioServicio.listarUsuarios();
        logger.info("Productos obtenidos:");
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }

    // Controlador para utilizar el metodo de guardar usuarios
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> agregarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        logger.info("Usuario a agregar" + dto);
        Usuario usuarioGuardado = this.usuarioServicio.guardarUsuario(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idUsuario}")
                .buildAndExpand(usuarioGuardado.getIdUsuario())
                .toUri();
        return ResponseEntity.created(ubicacion).body(usuarioGuardado);
    }

    // Controlador para utilizar el metodo de buscar usuario por id
    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(
            @PathVariable int idUsuario) {
        Usuario usuario = this.usuarioServicio.buscarUsuarioId(idUsuario);
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
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable int idUsuario,
            @Valid @RequestBody Usuario usuarioRecibido) {
        Usuario usuario = this.usuarioServicio.buscarUsuarioId(idUsuario);

        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el ID: " + idUsuario);
        }

        if (!usuarioRecibido.getRol().equals(usuario.getRol())) {
            throw new IllegalArgumentException("No está permitido modificar el rol del usuario.");
        }

        if (!usuarioRecibido.getUsuario().equals(usuario.getUsuario())) {
            throw new IllegalArgumentException("No está permitido modificar el nombre de usuario.");
        }
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setContraseña(usuarioRecibido.getContraseña());
        usuario.setCorreo(usuarioRecibido.getCorreo());
        usuario.setTelefono(usuarioRecibido.getTelefono());

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setUsuario(usuario.getUsuario());
        dto.setRol(usuario.getRol());
        dto.setContraseña(usuario.getContraseña());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());

        usuarioServicio.guardarUsuario(dto);

        return ResponseEntity.ok(usuario);
    }
    // Controlador para utilizar el metodo de actualizar la informacion de los usuarios
    @PutMapping("/administrador/{idUsuario}/credenciales")
    public ResponseEntity<Usuario> actualizarUsuarioAdmin(
            @PathVariable int idUsuario,
            @Valid @RequestBody Usuario usuarioRecibido) {
        if (usuarioRecibido == null) {
            throw new IllegalArgumentException("El cuerpo de la solicitud no puede estar vacio.");
        }
        Usuario usuario = this.usuarioServicio.buscarUsuarioId(idUsuario);
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + idUsuario);
        }
        // Actualiza todos los campos (ya que es administrador)
        usuario.setNombre(usuarioRecibido.getNombre());
        usuario.setUsuario(usuarioRecibido.getUsuario());
        usuario.setRol(usuarioRecibido.getRol());
        usuario.setContraseña(usuarioRecibido.getContraseña());
        usuario.setCorreo(usuarioRecibido.getCorreo());
        usuario.setTelefono(usuarioRecibido.getTelefono());

        // Guardar utilizando DTO si solo tienes ese método
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setUsuario(usuario.getUsuario());
        dto.setRol(usuario.getRol());
        dto.setContraseña(usuario.getContraseña());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());

        usuarioServicio.guardarUsuario(dto);

        return ResponseEntity.ok(usuario);
    }
}