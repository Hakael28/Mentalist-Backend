package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;
//import mt.mentalist.Funciones.Validacion;
import mt.mentalist.DTO.UsuarioDTO;
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
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }
    //    @Override
//    public Optional<Usuario> buscarUsuario(String usuario) {
//        return usuarioRepositorio.findByUsuario(usuario);
//    }
    @Override
    public Usuario buscarUsuarioId(Integer idUsuario) {
        return usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario actualizarUsuarioRestricciones(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = buscarUsuarioId(idUsuario);
        if (usuario==null){
            throw new RecursoNoEncontradoExcepcion("No encontro el usuario con el ID: "+ idUsuario );
        }
        if(!dto.getRol().equals(usuario.getRol())){
            throw new IllegalArgumentException("No está permitido modificar el rol del usuario.");
        }
        if(!dto.getUsuario().equals(usuario.getUsuario())){
            throw new IllegalArgumentException("No está permitido modificar el nombre de usuario.");
        }
        usuario.setNombre(dto.getNombre());
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario actulizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto) {
        Usuario usuario = buscarUsuarioId(idUsuario);
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el ID: " + idUsuario);
        }

        usuario.setNombre(dto.getNombre());
        usuario.setUsuario(dto.getUsuario());
        usuario.setRol(dto.getRol());
        usuario.setContraseña(dto.getContraseña());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        return usuarioRepositorio.save(usuario);
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
