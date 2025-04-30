
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.AreaOcurrenciaDTO;
import mt.mentalist.modelo.AreaOcurrencia;
import mt.mentalist.modelo.Eapb;

import javax.swing.plaf.PanelUI;


public interface IAreaOcurrenciaServicio {

    // Metodo para Listar Area de ocurrencia
    List<AreaOcurrenciaDTO> listarAreaOcureencia();

    // Metodo para seleccionar el ultimo registro del area de ocurrencia
    Optional<AreaOcurrencia> findTopByOrderByIdAreaOcurrenciaDesc();

    //Metodo para buscar Area de ocurrencia
    AreaOcurrenciaDTO buscarAreaOcurrencaId(Integer idAreaOcurrencia);

    //Metodo para guardar Area de ocurrencia
    AreaOcurrenciaDTO guardarAreaOcurrencia(AreaOcurrenciaDTO dto);

    //Metodo para eliminar Area de ocurrencia
    void eliminarAreaOcurrencia(Integer idAreaOcurrencia);


}


