package mt.mentalist.servicio.Basicos;

import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.modelo.Usuario;

import java.util.List;
import java.util.Optional;
//import mt.mentalist.Funciones.Validacion;


public interface IUsuarioServicio {

    // Método para listar todos los usuarios
    List<UsuarioDTO> listarUsuarios();
    // Metodo para seleccionar el ultimo  registro de usuario

    Optional<Usuario> findTopByOrderByIdUsuarioDesc();

    // Método para buscar un usuario por ID
    UsuarioDTO buscarUsuarioId(Integer idUsuario);

    // Método para guardar un usuario
    UsuarioDTO guardarUsuario(UsuarioDTO dto);

    // Actualiza datos de un usuario, sin permitir cambiar el rol ni el nombre de usuario
    UsuarioDTO actualizarUsuarioRestricciones(Integer idUsuario, UsuarioDTO dto);

    // Actualiza todos los datos del usuario (sin restricciones, para administrador)
    UsuarioDTO actulizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto);

    // Método para eliminar usuario por ID
    void eliminarUsuario(Integer idUsuario);

//    // Método para validar si existe un usuario por su nombre de usuario
//  boolean existeUsuario(String usuario);
//    // Método para buscar un usuario por Usuario
//   Optional<Usuario> buscarUsuario(String usuario);


//    // Metodo para validar ingreso del usuario
//   Validacion validarUsuario(String usuario, String contraseña, Rol rol);

}
