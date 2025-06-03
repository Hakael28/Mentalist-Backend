package mt.mentalist.controlador.Basics;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.RutaAtencionDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.servicio.Basicos.RutaAtencionServicio;
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
//http://localhost:8084/mentalist-web
@RequestMapping("mentalist-web")
@CrossOrigin(value = "http://localhost:4200")
public class RutaAtencionControlador {

    private static final Logger logger = LoggerFactory.getLogger(RutaAtencionControlador.class);

    @Autowired
    private RutaAtencionServicio rutaAtencionServicio;

    // Controlador pafra utilizar el metodo de listar Rutas de atención
    //http://localhost:8084/mentalist-web/usuarios
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/rutas")
    public List<RutaAtencionDTO> obtenerRutas() {
        List<RutaAtencionDTO> rutas = this.rutaAtencionServicio.listarRutas();
        logger.info("Rutas obtenidos:");
        rutas.forEach((ruta -> logger.info(ruta.toString())));
        return rutas;
    }

    // Controlador para utilizar el metodo de guardar Rutas de atención
    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/rutas")
    public ResponseEntity<RutaAtencionDTO> agregarRuta(@Valid @RequestBody RutaAtencionDTO dto) {
        logger.info("Ruta a agregar" + dto);
        RutaAtencionDTO respuesta = this.rutaAtencionServicio.guardarRuta(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idRuta}")
                .buildAndExpand(respuesta.getIdRutaAtencion())
                .toUri();
        return ResponseEntity.created(ubicacion).body(respuesta);
    }

    // Controlador para utilizar el metodo de buscar Rutas de atención por id
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/rutas/{idRutaAtencion}")
    public ResponseEntity<RutaAtencionDTO> obtenerRutaId(
            @PathVariable int idRutaAtencion) {
        RutaAtencionDTO ruta = this.rutaAtencionServicio.buscarRutaId(idRutaAtencion);
        if (ruta != null) {
            return ResponseEntity.ok(ruta);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + idRutaAtencion);
        }
    }

    // Controlador para utilizar el metodo de elimninar Rutas de atención
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/rutas/{idRutaAtencion}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarRuta(@PathVariable int idRutaAtencion) {
        rutaAtencionServicio.eliminarRuta(idRutaAtencion);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
