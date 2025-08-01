
package mt.mentalist.CursoVida;

import java.util.List;
import java.util.Optional;

public interface ICursoVidaServicio {

    // Metodo para listar cursovida
    List<CursoVidaDTO> listarCursoVida();

    // Metodo para seleccionar el ultimo registro curso vida
    Optional<CursoVida> findTopByOrderByIdCursoVidaDesc();

    //Metodo para buscar usuario
    CursoVidaDTO buscarCursoVidaId(Integer id_CursoVida);

    //Metodo para guarda cursovida
    CursoVidaDTO guardarCursoVida(CursoVidaDTO dto);

    //Metodo para eliminar cursvida
    void eliminarCursoVida(Integer idCursoVida);

}
      
