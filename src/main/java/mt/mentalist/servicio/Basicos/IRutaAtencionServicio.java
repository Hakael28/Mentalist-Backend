
package mt.mentalist.servicio.Basicos;

import mt.mentalist.DTO.DTOBasics.RutaAtencionDTO;
import mt.mentalist.modelo.Entidades.RutaAtencion;

import java.util.List;
import java.util.Optional;

public interface IRutaAtencionServicio {

    // Metodo para listar las rutas de atencion

    public List<RutaAtencionDTO> listarRutas();

    // Metodo para seleccionar el ultimo  registro de ruta de atencion..
    public Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc();

    //Metodo para buscar  las rutas de atencion  
    public RutaAtencionDTO buscarRutaId(Integer idRutaAtencion);

    //Metodo para guardar las rutas de atencion  
    public RutaAtencionDTO guardarRuta(RutaAtencionDTO dto);

    //Metodo para eliminar las rutas de atencion  
    public void eliminarRuta(Integer idRutaAtencion);
}
