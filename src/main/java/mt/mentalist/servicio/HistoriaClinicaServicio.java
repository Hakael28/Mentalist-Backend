package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.HistoriaClinicaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Caso;
import mt.mentalist.modelo.HistoriaClinica;
import mt.mentalist.modelo.Paciente;
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

    public List<HistoriaClinicaDTO> listarClinica() {
        List<HistoriaClinica> historiaClinicas = clinicaRepositorio.findAll();
          return historiaClinicas.stream()
                .map(this::convertirEntidadDTO)
                .toList();
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
    public HistoriaClinicaDTO buscarClinicaId(Integer idHistoriaClinica) {
        HistoriaClinica historiaClinica= clinicaRepositorio.findById(idHistoriaClinica)
                .orElseThrow(() -> {
                    return new RuntimeException("Historia clínica no encontrada con ID: " + idHistoriaClinica);
                });
        return convertirEntidadDTO(historiaClinica);
    }

    @Override
    public HistoriaClinicaDTO guardarClinica(HistoriaClinicaDTO dto) {

        Paciente paciente = pacienteServicio.obtenerPacienteEntidad(dto.getIdPaciente());
        if (paciente == null) {
            throw new RecursoNoEncontradoExcepcion("No encontro el paciente con el ID: " + dto.getIdPaciente());
        }

        Caso caso = casoServicio.buscarCasoId(dto.getIdCaso());
        if (caso == null) {
            throw new RecursoNoEncontradoExcepcion("No encontro el caso con el ID: " + dto.getIdCaso());
        }

        HistoriaClinica historiaClinica = new HistoriaClinica();

        historiaClinica.setPaciente(paciente);
        historiaClinica.setCaso(caso);
        historiaClinica.setDescripcionHistoria(dto.getDescripcionHistoria());

        HistoriaClinica historiaClinicaGuardada = clinicaRepositorio.save(historiaClinica);
        return convertirEntidadDTO(historiaClinicaGuardada);
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

    private HistoriaClinicaDTO convertirEntidadDTO(HistoriaClinica historiaClinica){
        HistoriaClinicaDTO dto =new HistoriaClinicaDTO();
        dto.setIdHistorialClinica(historiaClinica.getIdHistorialClinica());
        dto.setIdPaciente(historiaClinica.getPaciente().getIdPaciente());
        dto.setIdCaso(historiaClinica.getCaso().getIdCaso());
        dto.setDescripcionHistoria(historiaClinica.getDescripcionHistoria());
        return dto;
    }

}
