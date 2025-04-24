package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.RutaAtencionDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.RutaAtencion;
import mt.mentalist.servicio.RutaAtencionServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private static final Logger logger= LoggerFactory.getLogger(RutaAtencionControlador.class);

     @Autowired
     private RutaAtencionServicio rutaAtencionServicio;

    // Controlador pafra utilizar el metodo de listar Rutas de atención
    //http://localhost:8084/mentalist-web/usuarios
    @GetMapping("/rutas")
    public List<RutaAtencion> obtenerRutas() {
        List<RutaAtencion> rutas = this.rutaAtencionServicio.listarRutas();
        logger.info("Rutas obtenidos:");
        rutas.forEach((ruta -> logger.info(ruta.toString())));
        return rutas;
    }

    // Controlador para utilizar el metodo de guardar Rutas de atención
    @PostMapping("/rutas")
    public ResponseEntity<RutaAtencion> agregarRuta(@Valid @RequestBody RutaAtencionDTO dto) {
        logger.info("Ruta a agregar" + dto);
        RutaAtencion rutaGuardada = this.rutaAtencionServicio.guardarRuta(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idRuta}")
                .buildAndExpand(rutaGuardada.getIdRutaAtencion())
                .toUri();
        return ResponseEntity.created(ubicacion).body(rutaGuardada);
    }

    // Controlador para utilizar el metodo de buscar Rutas de atención por id
    @GetMapping("/rutas/{idRutaAtencion}")
    public ResponseEntity<RutaAtencion> obtenerRutaId(
            @PathVariable int idRutaAtencion) {
        RutaAtencion ruta = this.rutaAtencionServicio.buscarRutaId(idRutaAtencion);
        if (ruta != null) {
            return ResponseEntity.ok(ruta);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + idRutaAtencion);
        }
    }

    // Controlador para utilizar el metodo de elimninar Rutas de atención
    @DeleteMapping("/rutas/{idRutaAtencion}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarRuta(@PathVariable int idRutaAtencion) {
        rutaAtencionServicio.eliminarRuta(idRutaAtencion);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
