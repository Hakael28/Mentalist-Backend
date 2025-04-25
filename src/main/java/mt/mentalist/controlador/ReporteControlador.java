package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.ReporteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.servicio.ReporteServicio;
import mt.mentalist.servicio.UsuarioServicio;
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
public class ReporteControlador {

    private static final Logger logger= LoggerFactory.getLogger(ReporteControlador.class);

    @Autowired
    private ReporteServicio reporteServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Controlador para utilizar el metodo de listar los Reportes
    //http://localhost:8084/mentalist-web/usuarios
    @GetMapping("/reportes")
    public List<Reporte> obtenerReportes() {
        List<Reporte> reportes = this.reporteServicio.listarReportes();
        logger.info("Reportes obtenidos:");
        reportes.forEach((reporte -> logger.info(reporte.toString())));
        return reportes;
    }

    // Controlador para utilizar el metodo de guardar Reportes
    @PostMapping("/reportes")
    public ResponseEntity<Reporte> agregarReporte(@Valid @RequestBody ReporteDTO dto) {
        logger.info("Reporte a agregar" + dto);

        Usuario usuario = usuarioServicio.buscarUsuarioId(dto.getIdUsuario());
        if (usuario==null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el usuario con el Id: " + dto.getIdUsuario());
        }

        Reporte reporteGuardado = this.reporteServicio.guardarReporte(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idReporte}")
                .buildAndExpand(reporteGuardado.getIdReporte())
                .toUri();
        return ResponseEntity.created(ubicacion).body(reporteGuardado);
    }

    // Controlador para utilizar el metodo de buscar un Reporte por id
    @GetMapping("/reportes/{idReporte}")
    public ResponseEntity<Reporte> obtenerReporteId(
            @PathVariable int idReporte) {
        Reporte reporte= this.reporteServicio.buscarReporteId(idReporte);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el reporte con el id: " + idReporte);
        }
    }

    // Controlador para utilizar el metodo de elimninar un Reporte
    @DeleteMapping("/reportes/{idReporte}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarReporte(@PathVariable int idReporte) {
        reporteServicio.eliminarReporte(idReporte);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de un Reporte
    @PutMapping("/reportes/{idReporte}")
    public ResponseEntity<Reporte> actualizarReporte(
            @PathVariable int idReporte,
            @Valid @RequestBody ReporteDTO dto) {
        Reporte existente = reporteServicio.buscarReporteId(idReporte);
        if (existente == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el reporte con el id: " + idReporte);
        }

       // Reporte actualizado = reporteServicio.actualizarReporte(existente, dto);

        return null;
        //ResponseEntity.ok(actualizado);
    }
}
