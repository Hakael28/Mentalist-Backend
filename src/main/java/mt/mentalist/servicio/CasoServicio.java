package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.CasoDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.*;
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


    @Override
    public List<Caso> listarCaso() {
        List<Caso> casos = casoRepositorio.findAll();
        return casos;
    }

    @Override
    public Optional<Caso> findTopByOrderByIdCasoDesc() {
        return casoRepositorio.findTopByOrderByIdCasoDesc();
    }

    @Override
    public Caso buscarCasoId(Integer idCaso) {
        Caso caso = casoRepositorio.findById(idCaso).orElse(null);
        return caso;
    }

    @Override
    public Caso guardarCaso(CasoDTO dto) {
//        Entidades que viene del DTO (selecionados por el usuario y el sistema)
        Usuario usuario = usuarioServicio.buscarUsuarioId(dto.getIdUsuario());
        if (usuario== null){
            throw new RecursoNoEncontradoExcepcion("No encontro el usuario con el id: " + dto.getIdUsuario());
        }

        Paciente paciente = pacienteServicio.buscarPacientesId(dto.getIdPaciente());
        if (paciente== null){
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
        caso.setRemisionRutaSalud(dto.getRemisionRutaSalud());

//        Guardar el caso en la base de datos
       return casoRepositorio.save(caso);
    }


    @Override
    public void eliminarCaso(Integer idCaso) {
        Optional<Caso> caso = casoRepositorio.findById(idCaso);
        if(caso.isPresent()){
            casoRepositorio.deleteById(idCaso);
        }else {
            throw new RuntimeException("Usuario no encontrado con ID: " +idCaso);
        }
    }
}