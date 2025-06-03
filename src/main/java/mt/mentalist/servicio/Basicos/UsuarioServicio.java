package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;
import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.UsuarioRepositorio;
import mt.mentalist.servicio.Funciones.EncriptacionServicio;
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
        usuario.setContraseña(encriptacionServicio.encriptarContraseña(dto.getContraseña()));
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
        usuario.setContraseña(encriptacionServicio.encriptarContraseña(dto.getContraseña()));
        usuario.setCorreo(encriptacionServicio.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(encriptacionServicio.encriptarTexto(dto.getTelefono()));

        Usuario actulizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actulizado);
    }

    @Override
    public UsuarioDTO actulizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = ObtenerUsuarioEntidad(idUsuario);

        usuario.setNombre(encriptacionServicio.encriptarTexto(dto.getNombre()));
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContraseña(encriptacionServicio.encriptarContraseña(dto.getContraseña()));
        usuario.setCorreo(encriptacionServicio.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(encriptacionServicio.encriptarTexto(dto.getTelefono()));

        Usuario actulizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actulizado);
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

    @Override
    public Optional<Usuario> findTopByOrderByIdUsuarioDesc() {
        return usuarioRepositorio.findTopByOrderByIdUsuarioDesc();
    }

    private UsuarioDTO convertirEntidadDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(encriptacionServicio.desencriptarTexto(usuario.getNombre()));
        dto.setUsuario(usuario.getUsuario());
        dto.setRol(usuario.getRol());
        dto.setContraseña(usuario.getContraseña());
        dto.setCorreo(encriptacionServicio.desencriptarTexto(usuario.getCorreo()));
        dto.setTelefono(encriptacionServicio.desencriptarTexto(usuario.getTelefono()));
        return dto;
    }
    public Usuario ObtenerUsuarioEntidad(Integer idUsuario){
        return usuarioRepositorio.findById(idUsuario)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el usuario con el ID: " + idUsuario));
    }
}
