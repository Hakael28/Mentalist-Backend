package mt.mentalist.servicio;

import mt.mentalist.DTO.UsuarioDTO;
import mt.mentalist.modelo.Usuario;

import java.util.List;
import java.util.Optional;
//import mt.mentalist.Funciones.Validacion;


public interface IUsuarioServicio {

    // Método para listar todos los usuarios
    List<Usuario> listarUsuarios();
    // Metodo para seleccionar el ultimo  registro de usuario

    Optional<Usuario> findTopByOrderByIdUsuarioDesc();

//    // Método para buscar un usuario por Usuario
//    public Optional<Usuario> buscarUsuario(String usuario);

    // Método para buscar un usuario por ID
    public Usuario buscarUsuarioId(Integer idUsuario);

    // Método para guardar o actualizar un usuario
    public Usuario guardarUsuario(UsuarioDTO dto);

    // Método para eliminar usuario por ID
    public void eliminarUsuario(Integer idUsuario);

//    // Método para validar si existe un usuario por su nombre de usuario
//    public boolean existeUsuario(String usuario);

//    // Metodo para validar ingreso del usuario
//    public Validacion validarUsuario(String usuario, String contraseña, Rol rol);

}
