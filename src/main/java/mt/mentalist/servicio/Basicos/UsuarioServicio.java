package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;
import mt.mentalist.DTO.Basicas.UsuarioDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Entidades.Usuario;
import mt.mentalist.repositorio.UsuarioRepositorio;
import mt.mentalist.servicio.Seguridad.EncriptacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

  @Autowired
    private EncriptacionServicio encriptacionServicio;

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }
    @Override
    public UsuarioDTO buscarUsuarioId(Integer idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el usuario con el ID: " + idUsuario));
        return convertirEntidadDTO(usuario);
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNombre(encriptacionServicio.encriptarTexto(dto.getNombre()));
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContrasena(encriptacionServicio.encriptarContraseña(dto.getContrasena()));
        usuario.setCorreo(encriptacionServicio.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(encriptacionServicio.encriptarTexto(dto.getTelefono()));
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO actualizarUsuarioRestricciones(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = ObtenerUsuarioEntidad(idUsuario);
        if(!dto.getRol().equals(usuario.getRol())){
            throw new IllegalArgumentException("No está permitido modificar el rol del usuario.");
        }
        if(!dto.getUsuario().equals(usuario.getUsuario())){
            throw new IllegalArgumentException("No está permitido modificar el nombre de usuario.");
        }
        usuario.setNombre(encriptacionServicio.encriptarTexto(dto.getNombre()));
        usuario.setContrasena(encriptacionServicio.encriptarContraseña(dto.getContrasena()));
        usuario.setCorreo(encriptacionServicio.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(encriptacionServicio.encriptarTexto(dto.getTelefono()));

        Usuario actulizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actulizado);
    }

    @Override
    public UsuarioDTO actualizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = ObtenerUsuarioEntidad(idUsuario);

        // Verificar si el nombre de usuario ya existe en otro usuario
        Optional<Usuario> existente = usuarioRepositorio.findByUsuario(dto.getUsuario());
        if (existente.isPresent() && !existente.get().getIdUsuario().equals(idUsuario)) {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        }

        usuario.setNombre(encriptacionServicio.encriptarTexto(dto.getNombre()));
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());

        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            usuario.setContrasena(encriptacionServicio.encriptarContraseña(dto.getContrasena()));
        }

        usuario.setCorreo(encriptacionServicio.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(encriptacionServicio.encriptarTexto(dto.getTelefono()));

        Usuario actualizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actualizado);
    }


    @Override
    public void eliminarUsuario(Integer idUsuario){
       Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
       if(usuario.isPresent()){
           usuarioRepositorio.deleteById(idUsuario);
       }else {
           throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
       }
    }

    private UsuarioDTO convertirEntidadDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(encriptacionServicio.desencriptarTexto(usuario.getNombre()));
        dto.setUsuario(usuario.getUsuario());
        dto.setRol(usuario.getRol());
        dto.setContrasena(usuario.getContrasena());
        dto.setCorreo(encriptacionServicio.desencriptarTexto(usuario.getCorreo()));
        dto.setTelefono(encriptacionServicio.desencriptarTexto(usuario.getTelefono()));
        return dto;
    }
    public Usuario ObtenerUsuarioEntidad(Integer idUsuario){
        return usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontro el usuario con el ID: " + idUsuario));
    }
}
