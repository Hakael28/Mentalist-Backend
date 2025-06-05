
package mt.mentalist.servicio.Basicos;

import java.util.List;

import mt.mentalist.DTO.DTOBasics.CasoDTO;

public interface ICasoServicio {
    // Metodo para listar los casos
    List<CasoDTO> listarCaso();

    //Metodo para buscar el caso
    CasoDTO buscarCasoId(Integer idCaso);

    //Metodo para guardar el caso
    CasoDTO guardarCaso(CasoDTO dto);

    //Metodo para eliminar el caso
    void eliminarCaso(Integer idCaso);
}
