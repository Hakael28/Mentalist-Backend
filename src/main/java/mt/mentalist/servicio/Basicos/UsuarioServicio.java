package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;
//import mt.mentalist.Funciones.Validacion;
import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.Funciones.Encriptacion.Encriptacion;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

//    @Autowired
//    private final BCryptPasswordEncoder contraseñaEnconder;

    // Inyección de dependencias mediante constructor
    //public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
    //  this.usuarioRepositorio = usuarioRepositorio;
//        this.contraseñaEnconder = new BCryptPasswordEncoder();
    //}

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }
    //    @Override
//    public Optional<Usuario> buscarUsuario(String usuario) {
//        return usuarioRepositorio.findByUsuario(usuario);
//    }
    @Override
    public UsuarioDTO buscarUsuarioId(Integer idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el usuario con el ID: " + idUsuario));
        return convertirEntidadDTO(usuario);
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNombre(Encriptacion.encriptarTexto(dto.getNombre()));
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(Encriptacion.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(Encriptacion.desencriptarTexto(dto.getTelefono()));
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO actualizarUsuarioRestricciones(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = ObtenerUsuarioEntidad(idUsuario);
        if (usuario==null){
            throw new RecursoNoEncontradoExcepcion("No encontro el usuario con el ID: "+ idUsuario );
        }
        if(!dto.getRol().equals(usuario.getRol())){
            throw new IllegalArgumentException("No está permitido modificar el rol del usuario.");
        }
        if(!dto.getUsuario().equals(usuario.getUsuario())){
            throw new IllegalArgumentException("No está permitido modificar el nombre de usuario.");
        }
        usuario.setNombre(Encriptacion.encriptarTexto(dto.getNombre()));
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(Encriptacion.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(Encriptacion.encriptarTexto(dto.getTelefono()));

        Usuario actulizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actulizado);
    }

    @Override
    public UsuarioDTO actulizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = ObtenerUsuarioEntidad(idUsuario);
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el ID: " + idUsuario);
        }

        usuario.setNombre(Encriptacion.encriptarTexto(dto.getNombre()));
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(Encriptacion.encriptarTexto(dto.getCorreo()));
        usuario.setTelefono(Encriptacion.encriptarTexto(dto.getTelefono()));

        Usuario actulizado = usuarioRepositorio.save(usuario);
        return convertirEntidadDTO(actulizado);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario){
       Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
       if(usuario.isPresent()){
           usuarioRepositorio.deleteById(idUsuario);
       }else {
           throw new RuntimeException("Usuario no encontrado con ID: " +idUsuario);
       }
    }

    @Override
    public Optional<Usuario> findTopByOrderByIdUsuarioDesc() {
        return usuarioRepositorio.findTopByOrderByIdUsuarioDesc();
    }

    private UsuarioDTO convertirEntidadDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(Encriptacion.desencriptarTexto(usuario.getNombre()));
        dto.setUsuario(usuario.getUsuario());
        dto.setRol(usuario.getRol());
        dto.setContraseña(usuario.getContraseña());
        dto.setCorreo(Encriptacion.desencriptarTexto(usuario.getCorreo()));
        dto.setTelefono(Encriptacion.desencriptarTexto(usuario.getTelefono()));
        return dto;
    }
    public Usuario ObtenerUsuarioEntidad(Integer idUsuario){
        return usuarioRepositorio.findById(idUsuario)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el usuario con el ID: " + idUsuario));
    }
//    @Override
//    public boolean existeUsuario(String usuario) {
//        return usuarioRepositorio.existsByUsuario(usuario);
//    }

//    @Override
//    public Validacion validarUsuario(String usuario, String contraseña, Rol rol) {
//        try {
//            Optional<Usuario> usuarioOpt = usuarioRepositorio.findByUsuario(usuario);
//
//            if (usuarioOpt.isEmpty()) {
//                return new Validacion(false, "Usuario no encontrado", null); // Usuario null
//            }
//
//            Usuario usuarioEncontrado = usuarioOpt.get();
//
//            if (!usuarioEncontrado.getRol().equals(rol)) {
//                return new Validacion(false, "Rol incorrecto", null); //Usuario null
//            }
//
//            if (!usuarioEncontrado.getRol().equals(rol)) { // Comparar enums con .equals()
//                return new Validacion(false, "Rol incorrecto", null); // Usuario null
//            }
//
//            return new Validacion(true, "Autenticación exitosa", usuarioEncontrado); // Usuario encontrado
//
//        } catch (Exception e) {
//            return new Validacion(false, "Error en la validación: " + e.getMessage(), null); // Usuario null
//        }
//    }
}
