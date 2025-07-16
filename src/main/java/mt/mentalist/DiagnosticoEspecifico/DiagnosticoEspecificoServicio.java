
package mt.mentalist.DiagnosticoEspecifico;

import java.util.List;
import java.util.Optional;

import mt.mentalist.Funciones.Seguridad.EncriptacionServicio;
import mt.mentalist.Global.exception.RecursoNoEncontradoExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoEspecificoServicio implements IDiagnosticoEspecificoServicio {

    @Autowired
    private DiagnosticoEspecificoRepositorio diagnosticoEspecificoRepositorio;
    @Autowired
    private EncriptacionServicio encriptacionServicio;

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
        diagnosticoEspecifico.setTipoDiagnostico(dto.getTipoDiagnostico());
        diagnosticoEspecifico.setCodigoCie(encriptacionServicio.encriptarTexto(dto.getCodigoCie()));
        diagnosticoEspecifico.setObservacionesMedicas(encriptacionServicio.encriptarTexto(dto.getObservacionesMedicas()));
        diagnosticoEspecifico.setFechaDiagnostico(dto.getFechaDiagnostico());
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
        dto.setTipoDiagnostico(diagnosticoEspecifico.getTipoDiagnostico());
        dto.setCodigoCie(encriptacionServicio.desencriptarTexto(diagnosticoEspecifico.getCodigoCie()));
        dto.setObservacionesMedicas(encriptacionServicio.desencriptarTexto(diagnosticoEspecifico.getObservacionesMedicas()));
        dto.setFechaDiagnostico(diagnosticoEspecifico.getFechaDiagnostico());
        return dto;
    }
}