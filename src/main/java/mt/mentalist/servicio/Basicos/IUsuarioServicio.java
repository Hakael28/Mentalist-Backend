package mt.mentalist.servicio.Basicos;

import mt.mentalist.DTO.DTOBasics.UsuarioDTO;
import mt.mentalist.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServicio {

    // Método para listar todos los usuarios
    List<UsuarioDTO> listarUsuarios();
    // Metodo para seleccionar el ultimo  registro de usuario

    // Método para buscar un usuario por ID
    UsuarioDTO buscarUsuarioId(Integer idUsuario);

    // Método para guardar un usuario
    UsuarioDTO guardarUsuario(UsuarioDTO dto);

    // Actualiza datos de un usuario, sin permitir cambiar el rol ni el nombre de usuario
    UsuarioDTO actualizarUsuarioRestricciones(Integer idUsuario, UsuarioDTO dto);

    // Actualiza todos los datos del usuario (sin restricciones, para administrador)
    UsuarioDTO actualizarUsuarioAdmin(Integer idUsuario, UsuarioDTO dto);

    // Método para eliminar usuario por ID
    void eliminarUsuario(Integer idUsuario);

}
