package mt.mentalist.HistoriaClinica;

import java.util.List;
import java.util.Optional;

import mt.mentalist.Caso.CasoServicio;
import mt.mentalist.Paciente.PacienteServicio;
import mt.mentalist.Funciones.Seguridad.EncriptacionServicio;
import mt.mentalist.Global.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.Caso.Caso;
import mt.mentalist.Paciente.Paciente;
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
    @Autowired
    private EncriptacionServicio encriptacionServicio;

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

        Caso caso = casoServicio.obtenerCasoEntidad(dto.getIdCaso());
        if (caso == null) {
            throw new RecursoNoEncontradoExcepcion("No encontro el caso con el ID: " + dto.getIdCaso());
        }

        HistoriaClinica historiaClinica = new HistoriaClinica();

        historiaClinica.setPaciente(paciente);
        historiaClinica.setCaso(caso);
        historiaClinica.setDescripcionHistoria(encriptacionServicio.encriptarTexto(dto.getDescripcionHistoria()));

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
        dto.setDescripcionHistoria(encriptacionServicio.desencriptarTexto(historiaClinica.getDescripcionHistoria()));
        return dto;
    }

}
