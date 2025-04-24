
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.EapbDTO;
import mt.mentalist.modelo.Eapb;


public interface IEapbServicio {
    // Metodo para listar las eapb
    public List<Eapb> listarEapb();

    // Metodo para seleccionar el ultimo  registro de Eapb
    public Optional<Eapb> findTopByOrderByIdEapbDesc();

    //Metodo para buscar la eapb
    public Eapb buscarEapbId(Integer idEapb);

    //Metodo para guardar la eapb
    public Eapb guardarEapb(EapbDTO dto);

    //Metodo para eliminar la eapb
    public void eliminarEapb(Integer idEapb);
}
