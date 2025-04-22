
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.CasoDTO;
import mt.mentalist.modelo.Caso;

public interface ICasoServicio {
    // Metodo para listar los casos
    public List<Caso> listarCaso();

    // Metodo para sellecionar el ultimo caso registrado
    Optional<Caso> findTopByOrderByIdCasoDesc();

    //Metodo para buscar el caso
    public Caso buscarCasoId(Integer idCaso);

    //Metodo para guardar el caso
    public Caso guardarCaso(CasoDTO dto);

    //Metodo para eliminar el caso
    public void eliminarCaso(Integer idCaso);
}
