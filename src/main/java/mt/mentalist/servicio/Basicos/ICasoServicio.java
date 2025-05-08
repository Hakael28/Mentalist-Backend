
package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DTOBasics.CasoDTO;
import mt.mentalist.modelo.Caso;

public interface ICasoServicio {
    // Metodo para listar los casos
    List<CasoDTO> listarCaso();

    // Metodo para sellecionar el ultimo caso registrado
    Optional<Caso> findTopByOrderByIdCasoDesc();

    //Metodo para buscar el caso
    CasoDTO buscarCasoId(Integer idCaso);

    //Metodo para guardar el caso
    CasoDTO guardarCaso(CasoDTO dto);

    //Metodo para eliminar el caso
    void eliminarCaso(Integer idCaso);
}
