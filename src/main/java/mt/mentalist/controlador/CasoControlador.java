package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.CasoDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.*;
import mt.mentalist.servicio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8084/mentalist-web
@RequestMapping("mentalist-web")
@CrossOrigin(value = "http://localhost:4200")
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
    @GetMapping("/casos")
    public List<Caso> Obtenercasos() {
        List<Caso> casos = this.casoServicio.listarCaso();
        logger.info("Reportes obtenidos:");
        casos.forEach((caso -> logger.info(caso.toString())));
        return casos;
    }

    //http://localhost:8084/mentalist-web/casos
    // Controlador para utilizar el metodo de guardar casos
    @PostMapping("/casos")
    public ResponseEntity<Caso> agregarCaso(@Valid @RequestBody CasoDTO dto) {
        logger.info("Intentando registrar un nuevo caso con datos: {}", dto);

        // Solo se envía el DTO. La lógica de buscar el paciente, usuario y las entidades
        // más recientes está completamente en el servicio.
        Caso casoGuardado = casoServicio.guardarCaso(dto);

        logger.info("Caso registrado correctamente con ID: {}", casoGuardado.getIdCaso());
        return ResponseEntity.ok(casoGuardado);
    }
}