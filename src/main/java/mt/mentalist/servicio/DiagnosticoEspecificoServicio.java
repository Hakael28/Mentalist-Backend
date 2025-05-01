
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DiagnosticoEspecificoDTO;
import mt.mentalist.Funciones.Encriptacion.Encriptacion;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.DiagnosticoEspecifico;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.DiagnosticoEspecificoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoEspecificoServicio implements IDiagnosticoEspecificoServicio {

    @Autowired
    private DiagnosticoEspecificoRepositorio diagnosticoEspecificoRepositorio;

    @Override
    public List<DiagnosticoEspecificoDTO> listarDiagnosticoEspecifico() {
        List<DiagnosticoEspecifico> diagnosticoEspecificos = diagnosticoEspecificoRepositorio.findAll();
        return diagnosticoEspecificos.stream().map(this::convertirEntidadDTO)
                .toList();
    }

    @Override
    public Optional<DiagnosticoEspecifico> findTopByOrderByIdDiagnosticoEspecificoDesc() {
        return diagnosticoEspecificoRepositorio.findTopByOrderByIdDiagnosticoEspecificoDesc();
    }

    @Override
    public DiagnosticoEspecificoDTO buscarDiagnosticoEspecificoId(Integer idDianosticoEspecifico) {
        DiagnosticoEspecifico diagnosticoEspecifico = diagnosticoEspecificoRepositorio.findById(idDianosticoEspecifico)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el diagnostico especifico con el ID : "+ idDianosticoEspecifico));

        return convertirEntidadDTO(diagnosticoEspecifico);
    }

    @Override
    public DiagnosticoEspecificoDTO guardarDiagnosticoEspecifico(DiagnosticoEspecificoDTO dto) {
        DiagnosticoEspecifico diagnosticoEspecifico = new DiagnosticoEspecifico();
        diagnosticoEspecifico.setTipodiagnostico(dto.getTipodiagnostico());
        diagnosticoEspecifico.setCodigoCie(Encriptacion.encriptarTexto(dto.getCodigoCie()));
        diagnosticoEspecifico.setObservacionesMedicas(Encriptacion.encriptarTexto(dto.getObservacionesMedicas()));
        diagnosticoEspecifico.setFechadiagnostico(dto.getFechadiagnostico());
        DiagnosticoEspecifico diagnosticoEspecificoGuardado =diagnosticoEspecificoRepositorio.save(diagnosticoEspecifico);
        return convertirEntidadDTO(diagnosticoEspecificoGuardado);
    }

    @Override
    public void eliminarDiagnosticoEspecifico(Integer idDiagnosticoEspecifico) {
        Optional<DiagnosticoEspecifico> diagnosticoEspecifico = diagnosticoEspecificoRepositorio.findById(idDiagnosticoEspecifico);
        if(diagnosticoEspecifico.isPresent()){
            diagnosticoEspecificoRepositorio.deleteById(idDiagnosticoEspecifico);
        }else {
            throw new RuntimeException("Diagnostico no encontrado con ID: " +idDiagnosticoEspecifico);
        }
    }

    private DiagnosticoEspecificoDTO convertirEntidadDTO(DiagnosticoEspecifico diagnosticoEspecifico){
        DiagnosticoEspecificoDTO dto = new DiagnosticoEspecificoDTO();
        dto.setIdDiagnosticoEspecifico(diagnosticoEspecifico.getIdDiagnosticoEspecifico());
        dto.setTipodiagnostico(diagnosticoEspecifico.getTipodiagnostico());
        dto.setCodigoCie(Encriptacion.desencriptarTexto(diagnosticoEspecifico.getCodigoCie()));
        dto.setObservacionesMedicas(Encriptacion.desencriptarTexto(diagnosticoEspecifico.getObservacionesMedicas()));
        dto.setFechadiagnostico(diagnosticoEspecifico.getFechadiagnostico());
        return dto;
    }
}