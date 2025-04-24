package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.HistoriaClinicaDTO;
import mt.mentalist.modelo.HistoriaClinica;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.repositorio.HistoriaClinicaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaServicio implements IHistoriaClinicaServicio {

    @Autowired
    private HistoriaClinicaRepositorio clinicaRepositorio;
    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private CasoServicio casoServicio;

    @Override

    public List<HistoriaClinica> listarClinica() {
        return clinicaRepositorio.findAll();
    }

    @Override
    public List<HistoriaClinica> obtenerHistoriasClinicasPorPaciente(Integer idPaciente) {
        return clinicaRepositorio.findByPaciente_IdPaciente(idPaciente);
    }

    @Override
    public Optional<HistoriaClinica> findTopByOrderByIdHistorialClinicaDesc() {
        return clinicaRepositorio.findTopByOrderByIdHistorialClinicaDesc();
    }

    @Override
    public HistoriaClinica buscarClinicaId(Integer idHistoriaClinica) {
        return clinicaRepositorio.findById(idHistoriaClinica)
                .orElseThrow(() -> {
                    return new RuntimeException("Historia clínica no encontrada con ID: " + idHistoriaClinica);
                });
    }

    @Override
    public HistoriaClinica guardarClinica(HistoriaClinicaDTO dto) {
        HistoriaClinica historiaClinica = new HistoriaClinica();

        historiaClinica.setPaciente(pacienteServicio.buscarPacientesId(dto.getIdPaciente()));
        historiaClinica.setCaso(casoServicio.buscarCasoId(dto.getIdCaso()));
        historiaClinica.setDescripcionHistoria(dto.getDescripcionHistoria());
        HistoriaClinica historiaGuardada = clinicaRepositorio.save(historiaClinica);
        return historiaGuardada;
    }


    @Override
    public void eliminarClinica(Integer idHistoriaClinica) {
        Optional<HistoriaClinica> historiaClinica = clinicaRepositorio.findById(idHistoriaClinica);
        if (historiaClinica.isPresent()) {
            clinicaRepositorio.deleteById(idHistoriaClinica);
        } else {
            throw new RuntimeException("Historia clinica no encontrada con ID: " + idHistoriaClinica);
        }
    }


}
