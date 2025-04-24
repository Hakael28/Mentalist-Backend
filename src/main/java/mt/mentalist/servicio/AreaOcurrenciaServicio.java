
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.AreaOcurrenciaDTO;
import mt.mentalist.modelo.AreaOcurrencia;
import mt.mentalist.modelo.Eapb;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.AreaOcurrenciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AreaOcurrenciaServicio implements IAreaOcurrenciaServicio {
    @Autowired
    private AreaOcurrenciaRepositorio areaOcurrenciaRepositorio;

    @Override
    public List<AreaOcurrencia> listarAreaOcureencia() {
        List<AreaOcurrencia> Areaocurrencia = areaOcurrenciaRepositorio.findAll();
        return Areaocurrencia;
    }

    @Override
    public Optional<AreaOcurrencia> findTopByOrderByIdAreaOcurrenciaDesc() {
        return areaOcurrenciaRepositorio.findTopByOrderByIdAreaOcurrenciaDesc();
    }


    @Override
    public AreaOcurrencia buscarAreaOcurrencaId(Integer idAreaOcurrencia) {
        AreaOcurrencia Areaocureencia = areaOcurrenciaRepositorio.findById(idAreaOcurrencia).orElse(null);
        return Areaocureencia;
    }

    @Override
    public AreaOcurrencia guardarAreaOcurrencia(AreaOcurrenciaDTO dto) {
        AreaOcurrencia areaOcurrencia = new AreaOcurrencia();
        areaOcurrencia.setNombre(dto.getNombre());
        AreaOcurrencia areaOcurrenciaGuardada = areaOcurrenciaRepositorio.save(areaOcurrencia);
        return areaOcurrenciaGuardada;
    }

    @Override
    public void eliminarAreaOcurrencia(Integer idAreaOcurrencia) {
        Optional<AreaOcurrencia> areaOcurrencia = areaOcurrenciaRepositorio.findById(idAreaOcurrencia);
        if(areaOcurrencia.isPresent()){
            areaOcurrenciaRepositorio.deleteById(idAreaOcurrencia);
        }else {
            throw new RuntimeException("Usuario no encontrado con ID: " +idAreaOcurrencia);
        }
    }
}