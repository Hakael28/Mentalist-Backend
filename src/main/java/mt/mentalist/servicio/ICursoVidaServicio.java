
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.CursoVidaDTO;
import mt.mentalist.modelo.CursoVida;

public interface ICursoVidaServicio {

    // Metodo para listar cursovida
    public List<CursoVida> listarCursoVida();

    // Metodo para seleccionar el ultimo registro curso vida
    Optional<CursoVida> findTopByOrderByIdCursoVidaDesc();

    //Metodo para buscar usuario
    public CursoVida buscarCursoVidaId(Integer id_CursoVida);

    //Metodo para guarda cursovida
    public CursoVida guardarCursoVida(CursoVidaDTO dto);

    //Metodo para eliminar cursvida
    public void eliminarCursoVida(Integer idCursoVida);

}
      
