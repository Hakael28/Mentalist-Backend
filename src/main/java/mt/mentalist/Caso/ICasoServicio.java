
package mt.mentalist.Caso;

import java.util.List;

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
