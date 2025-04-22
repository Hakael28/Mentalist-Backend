
package mt.mentalist.servicio;

import mt.mentalist.DTO.RutaAtencionDTO;
import mt.mentalist.modelo.RutaAtencion;

import java.util.List;
import java.util.Optional;

public interface IRutaAtencionServicio {

    // Metodo para listar las rutas de atencion

    public List<RutaAtencion> listarRutas();

    // Metodo para seleccionar el ultimo  registro de ruta de atencion..
    public Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc();

    //Metodo para buscar  las rutas de atencion  
    public RutaAtencion buscarRutaId(Integer idRutaAtencion);

    //Metodo para guardar las rutas de atencion  
    public RutaAtencion guardarRuta(RutaAtencionDTO dto);

    //Metodo para eliminar las rutas de atencion  
    public void eliminarRuta(Integer idRutaAtencion);
}
