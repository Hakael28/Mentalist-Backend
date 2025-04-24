
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DiagnosticoEspecificoDTO;
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
    public List<DiagnosticoEspecifico> listarDiagnosticoEspecifico() {
        return diagnosticoEspecificoRepositorio.findAll();
    }

    @Override
    public Optional<DiagnosticoEspecifico> findTopByOrderByIdDiagnosticoEspecificoDesc() {
        return diagnosticoEspecificoRepositorio.findTopByOrderByIdDiagnosticoEspecificoDesc();
    }

    public DiagnosticoEspecifico buscarDiagnosticoEspecificoId() {
        return buscarDiagnosticoEspecificoId(null);
    }

    @Override
    public DiagnosticoEspecifico buscarDiagnosticoEspecificoId(Integer idDianosticoEspecifico) {
        DiagnosticoEspecifico diagnosticoespecifico = diagnosticoEspecificoRepositorio.findById(idDianosticoEspecifico).orElse(null);
        return diagnosticoespecifico;
    }

    @Override
    public DiagnosticoEspecifico guardarDiagnosticoEspecifico(DiagnosticoEspecificoDTO dto) {
        DiagnosticoEspecifico diagnosticoEspecifico = new DiagnosticoEspecifico();
        diagnosticoEspecifico.setTipodiagnostico(dto.getTipodiagnostico());
        diagnosticoEspecifico.setCodigoCie(dto.getCodigoCie());
        diagnosticoEspecifico.setObservacionesMedicas(dto.getObservacionesMedicas());
        diagnosticoEspecifico.setFechadiagnostico(dto.getFechadiagnostico());
        DiagnosticoEspecifico diagnosticoEspecificoGuardado= diagnosticoEspecificoRepositorio.save(diagnosticoEspecifico);
        return diagnosticoEspecificoGuardado;
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

}