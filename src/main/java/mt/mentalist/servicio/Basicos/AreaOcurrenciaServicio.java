
package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.Basicas.AreaOcurrenciaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Entidades.AreaOcurrencia;
import mt.mentalist.repositorio.AreaOcurrenciaRepositorio;
import mt.mentalist.servicio.Seguridad.EncriptacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AreaOcurrenciaServicio implements IAreaOcurrenciaServicio {
    @Autowired
    private AreaOcurrenciaRepositorio areaOcurrenciaRepositorio;
    @Autowired
    private EncriptacionServicio encriptacionServicio;

    @Override
    public List<AreaOcurrenciaDTO> listarAreaOcureencia() {
        List<AreaOcurrencia> areaOcurrencias = areaOcurrenciaRepositorio.findAll();
        return areaOcurrencias.stream().map(this::convertirEntidadDTO).toList();
    }

    @Override
    public Optional<AreaOcurrencia> findTopByOrderByIdAreaOcurrenciaDesc() {
        return areaOcurrenciaRepositorio.findTopByOrderByIdAreaOcurrenciaDesc();
    }


    @Override
    public AreaOcurrenciaDTO buscarAreaOcurrencaId(Integer idAreaOcurrencia) {
        AreaOcurrencia areaOcurrencia = areaOcurrenciaRepositorio.findById(idAreaOcurrencia)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontro el area de ocurrencia con el ID: " + idAreaOcurrencia));
        return convertirEntidadDTO(areaOcurrencia);
    }

    @Override
    public AreaOcurrenciaDTO guardarAreaOcurrencia(AreaOcurrenciaDTO dto) {
        AreaOcurrencia areaOcurrencia = new AreaOcurrencia();
        areaOcurrencia.setNombre(encriptacionServicio.encriptarTexto(dto.getNombre()));
        AreaOcurrencia areaOcurrenciaGuardada = areaOcurrenciaRepositorio.save(areaOcurrencia);
        return convertirEntidadDTO(areaOcurrenciaGuardada);
    }

    @Override
    public void eliminarAreaOcurrencia(Integer idAreaOcurrencia) {
        Optional<AreaOcurrencia> areaOcurrencia = areaOcurrenciaRepositorio.findById(idAreaOcurrencia);
        if (areaOcurrencia.isPresent()) {
            areaOcurrenciaRepositorio.deleteById(idAreaOcurrencia);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + idAreaOcurrencia);
        }
    }

    private AreaOcurrenciaDTO convertirEntidadDTO(AreaOcurrencia areaOcurrencia) {
        AreaOcurrenciaDTO dto = new AreaOcurrenciaDTO();
        dto.setIdAreaOcurrencia(areaOcurrencia.getIdAreaOcurrencia());
        dto.setNombre(encriptacionServicio.desencriptarTexto(areaOcurrencia.getNombre()));
        return dto;
    }
}