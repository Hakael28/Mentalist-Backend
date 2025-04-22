package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.CasoDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Caso;
import mt.mentalist.modelo.Usuario;
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
        Caso caso = new Caso();
//        Entidades que viene del DTO (selecionados por el usuario y el sistema)
        caso.setPaciente(pacienteServicio.buscarPacientesId(dto.getIdPaciente()));
        caso.setUsuario(usuarioServicio.buscarUsuarioId(dto.getIdUsuario()));

//        Entidades que se toman el ultimo registro insertado
        caso.setAreaOcurrencia(areaOcurrenciaServicio.findTopByOrderByIdAreaOcurrenciaDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en área de ocurrencia")));
        caso.setRutaAtencion(rutaAtencionServicio.findTopByOrderByIdRutaAtencionDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en ruta de atencion")));

        caso.setEapb(eapbServicio.findTopByOrderByIdEapbDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en eapb")));
        caso.setCursoVida(cursoVidaServicio.findTopByOrderByIdCursoVidaDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en curso de vida")));

        caso.setDiagnostico(diagnosticoEspecificoServicio.findTopByOrderByIdDiagnosticoEspecificoDesc()
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No hay registros en diagnostico especifico")));

//        Datos propios del caso
        caso.setFechaNotificacion(dto.getFechaNotificacion());
        caso.setSemanaEpidemiologica(dto.getSemanaEpidemiologica());
        caso.setFechaIngreso(dto.getFechaIngreso());
        caso.setFechaRevisionHistoria(dto.getFechaRevisionHistoria());
        caso.setRemisionRutaSalud(dto.getRemisionRutaSalud());

//        Guardar el caso en la base de datos
       Caso casoGuardado = casoRepositorio.save(caso);
       return casoGuardado;
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