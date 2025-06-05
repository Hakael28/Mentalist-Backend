
package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DTOBasics.EapbDTO;
import mt.mentalist.modelo.Entidades.Eapb;


public interface IEapbServicio {
    // Metodo para listar las eapb
    public List<EapbDTO> listarEapb();

    // Metodo para seleccionar el ultimo  registro de Eapb
    public Optional<Eapb> findTopByOrderByIdEapbDesc();

    //Metodo para buscar la eapb
    public EapbDTO buscarEapbId(Integer idEapb);

    //Metodo para guardar la eapb
    public EapbDTO guardarEapb(EapbDTO dto);

    //Metodo para eliminar la eapb
    public void eliminarEapb(Integer idEapb);
}
