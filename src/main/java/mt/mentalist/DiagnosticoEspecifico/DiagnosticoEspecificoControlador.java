package mt.mentalist.DiagnosticoEspecifico;

import jakarta.validation.Valid;
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
public class DiagnosticoEspecificoControlador {
    private static final Logger logger = LoggerFactory.getLogger(DiagnosticoEspecificoControlador.class);

    @Autowired
    private DiagnosticoEspecificoServicio diagnosticoEspecificoServicio;

    // Controlador para utilizar el metodo de listar Diagnostico especifico
    //http://localhost:8084/mentalist-web/basicos/diagnosticos
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/diagnosticos")
    public List<DiagnosticoEspecificoDTO> obtenediagnosticos() {
        List<DiagnosticoEspecificoDTO> diagnosticos = this.diagnosticoEspecificoServicio.listarDiagnosticoEspecifico();
        logger.info("DiagnosticoEspecifico obtenidos:");
        diagnosticos.forEach((diagnostico -> logger.info(diagnostico.toString())));
        return diagnosticos;

    }

    // Controlador para utilizar el metodo de guardar diagnosticos
    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/diagnosticos")
    public ResponseEntity<DiagnosticoEspecificoDTO> agregarDianosticoEsoecifico(@Valid @RequestBody DiagnosticoEspecificoDTO dto) {
        logger.info("DiagnosticoEspecifico a agregar" + dto);
        DiagnosticoEspecificoDTO diagnosticoEspecificoGuardado = this.diagnosticoEspecificoServicio.guardarDiagnosticoEspecifico(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idDiagnosticoEspecifico}")
                .buildAndExpand(diagnosticoEspecificoGuardado.getIdDiagnosticoEspecifico())
                .toUri();
        return ResponseEntity.created(ubicacion).body(diagnosticoEspecificoGuardado);

    }

    // Controlador para utilizar el metodo de buscar Dianostico especifico por id
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/diagnosticos/{idDiagnosticoEspecifico}")
    public ResponseEntity<DiagnosticoEspecificoDTO> obtenerDiagnosticoEspecificoid(
            @PathVariable int idDiagnosticoEspecifico) {
        DiagnosticoEspecificoDTO diagnosticoEspecifico = this.diagnosticoEspecificoServicio.buscarDiagnosticoEspecificoId(idDiagnosticoEspecifico);
        if (diagnosticoEspecifico != null) {
            return ResponseEntity.ok(diagnosticoEspecifico);
        } else {
            throw new RecursoNoEncontradoExcepcion("no se encontro el diagnostico especifico con el id");
        }
    }
    
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/diagnosticos/{idDiagnosticoEspecifico}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarDiagnosticoEspeficio(@PathVariable int idDiagnosticoEspecifico) {
        diagnosticoEspecificoServicio.eliminarDiagnosticoEspecifico(idDiagnosticoEspecifico);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}






