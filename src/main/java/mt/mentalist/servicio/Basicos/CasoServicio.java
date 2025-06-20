package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.Basicas.CasoDTO;
import mt.mentalist.modelo.Entidades.*;
import mt.mentalist.servicio.Seguridad.EncriptacionServicio;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasoServicio implements ICasoServicio {
    @Autowired
    private CasoRepositorio casoRepositorio;
    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private AreaOcurrenciaServicio areaOcurrenciaServicio;
    @Autowired
    private RutaAtencionServicio rutaAtencionServicio;
    @Autowired
    private EapbServicio eapbServicio;
    @Autowired
    private CursoVidaServicio cursoVidaServicio;
    @Autowired
    private DiagnosticoEspecificoServicio diagnosticoEspecificoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private EncriptacionServicio encriptacionServicio;


    @Override
    public List<CasoDTO> listarCaso() {
        List<Caso> casos = casoRepositorio.findAll();
        return casos.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }

    @Override
    public CasoDTO buscarCasoId(Integer idCaso) {
        Caso caso = casoRepositorio.findById(idCaso).orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontro el caso con el ID: " + idCaso));
        return convertirEntidadDTO(caso);
    }

    @Override
    public CasoDTO guardarCaso(CasoDTO dto) {
//        Entidades que viene del DTO (selecionados por el usuario y el sistema)
        Usuario usuario = usuarioServicio.ObtenerUsuarioEntidad(dto.getIdUsuario());
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No encontro el usuario con el id: " + dto.getIdUsuario());
        }

        Paciente paciente = pacienteServicio.obtenerPacienteEntidad(dto.getIdPaciente());
        if (paciente == null) {
            throw new RecursoNoEncontradoExcepcion("No encontro el paciente con el id: " + dto.getIdPaciente());
        }

//        Entidades que se toman el ultimo registro insertado
        AreaOcurrencia areaOcurrencia = areaOcurrenciaServicio.findTopByOrderByIdAreaOcurrenciaDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en área de ocurrencia"));
        RutaAtencion rutaAtencion = rutaAtencionServicio.findTopByOrderByIdRutaAtencionDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en ruta de atencion"));

        Eapb eapb = eapbServicio.findTopByOrderByIdEapbDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en eapb"));
        CursoVida cursoVida = cursoVidaServicio.findTopByOrderByIdCursoVidaDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en curso de vida"));

        DiagnosticoEspecifico diagnosticoEspecifico = diagnosticoEspecificoServicio.findTopByOrderByIdDiagnosticoEspecificoDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en diagnostico especifico"));


        Caso caso = new Caso();
        caso.setUsuario(usuario);
        caso.setPaciente(paciente);
        caso.setAreaOcurrencia(areaOcurrencia);
        caso.setRutaAtencion(rutaAtencion);
        caso.setEapb(eapb);
        caso.setCursoVida(cursoVida);
        caso.setDiagnostico(diagnosticoEspecifico);

//        Datos propios del caso
        caso.setFechaNotificacion(dto.getFechaNotificacion());
        caso.setSemanaEpidemiologica(dto.getSemanaEpidemiologica());
        caso.setFechaIngreso(dto.getFechaIngreso());
        caso.setFechaRevisionHistoria(dto.getFechaRevisionHistoria());
        caso.setRemisionRutaSalud(encriptacionServicio.encriptarTexto(dto.getRemisionRutaSalud()));

//        Guardar el caso en la base de datos
        Caso casoGuardado = casoRepositorio.save(caso);
        return convertirEntidadDTO(caso);
    }


    @Override
    public void eliminarCaso(Integer idCaso) {
        Optional<Caso> caso = casoRepositorio.findById(idCaso);
        if (caso.isPresent()) {
            casoRepositorio.deleteById(idCaso);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + idCaso);
        }
    }

    private CasoDTO convertirEntidadDTO(Caso caso) {
        CasoDTO dto = new CasoDTO();
        dto.setIdCaso(caso.getIdCaso());
        dto.setIdPaciente(caso.getPaciente().getIdPaciente());
        dto.setIdAreaOcurrencia(caso.getAreaOcurrencia().getIdAreaOcurrencia());
        dto.setIdRutaAtencion(caso.getRutaAtencion().getIdRutaAtencion());
        dto.setIdEapb(caso.getEapb().getIdEapb());
        dto.setIdCursoVida(caso.getCursoVida().getIdCursoVida());
        dto.setIdDiagnosticoEspecifico(caso.getDiagnostico().getIdDiagnosticoEspecifico());
        dto.setIdUsuario(caso.getUsuario().getIdUsuario());
        dto.setFechaNotificacion(caso.getFechaNotificacion());
        dto.setSemanaEpidemiologica(caso.getSemanaEpidemiologica());
        dto.setFechaIngreso(caso.getFechaIngreso());
        dto.setFechaRevisionHistoria(caso.getFechaRevisionHistoria());
        dto.setRemisionRutaSalud(encriptacionServicio.desencriptarTexto(caso.getRemisionRutaSalud()));
        return dto;
    }

    public Caso obtenerCasoEntidad(Integer idCaso){
        return casoRepositorio.findById(idCaso)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el caso con el ID: " + idCaso));
    }
}