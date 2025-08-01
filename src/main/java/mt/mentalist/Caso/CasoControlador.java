package mt.mentalist.Caso;

import jakarta.validation.Valid;
import mt.mentalist.CursoVida.CursoVidaServicio;
import mt.mentalist.AreaOcurrencia.AreaOcurrenciaServicio;
import mt.mentalist.DiagnosticoEspecifico.DiagnosticoEspecificoServicio;
import mt.mentalist.Eapb.EapbServicio;
import mt.mentalist.Paciente.PacienteServicio;
import mt.mentalist.RutaAtencion.RutaAtencionServicio;
import mt.mentalist.Usuario.UsuarioServicio;
import mt.mentalist.Global.exception.RecursoNoEncontradoExcepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8084/mentalist-web/basicos
@RequestMapping("/mentalist-web/basicos")
public class CasoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(CasoControlador.class);

    @Autowired
    private CasoServicio casoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
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

    // Controlador para utilizar el metodo de listar los casos
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/casos")
    public List<CasoDTO> Obtenercasos() {
        List<CasoDTO> casos = this.casoServicio.listarCaso();
        logger.info("Reportes obtenidos:");
        casos.forEach((caso -> logger.info(caso.toString())));
        return casos;
    }

    //http://localhost:8084/mentalist-web/casos
    // Controlador para utilizar el metodo de guardar casos
    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/casos")
    public ResponseEntity<CasoDTO> agregarCaso(@Valid @RequestBody CasoDTO dto) {
        logger.info("Intentando registrar un nuevo caso con datos: {}", dto);

        // Solo se envía el DTO. La lógica de buscar el paciente, usuario y las entidades
        // más recientes está completamente en el servicio.
        CasoDTO casoGuardado = casoServicio.guardarCaso(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idCaso}")
                .buildAndExpand(casoGuardado.getIdCaso())
                .toUri();
        logger.info("Caso registrado correctamente con ID: {}", casoGuardado.getIdCaso());
        return ResponseEntity.created(ubicacion).body(casoGuardado);
    }

    // Controlador para utilizar el metodo de buscar caso por id
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/casos/{idCaso}")
    public ResponseEntity<CasoDTO> obtenerCasoId(
            @PathVariable int idCaso) {
        CasoDTO caso = this.casoServicio.buscarCasoId(idCaso);
        if (caso != null) {
            return ResponseEntity.ok(caso);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el caso con el id: " + idCaso);
        }
    }

    // Controlador para utilizar el metodo de eliminar caso
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/casos/{idCaso}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarCaso(@PathVariable int idCaso) {
        casoServicio.eliminarCaso(idCaso);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}