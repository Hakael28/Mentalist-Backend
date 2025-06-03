package mt.mentalist.controlador.Basicos;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.ReporteDTO;
import mt.mentalist.servicio.Basicos.ReporteServicio;
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
public class ReporteControlador {

    private static final Logger logger = LoggerFactory.getLogger(ReporteControlador.class);

    @Autowired
    private ReporteServicio reporteServicio;


    // Controlador para utilizar el metodo de listar los Reportes
    //http://localhost:8084/mentalist-web/basicos/reportes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reportes")
    public List<ReporteDTO> obtenerReportes() {
        List<ReporteDTO> reportes = this.reporteServicio.listarReportes();
        logger.info("Reportes obtenidos:");
        reportes.forEach((reporte -> logger.info(reporte.toString())));
        return reportes;
    }

    // Controlador para utilizar el metodo de guardar Reportes
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICO')")
    @PostMapping("/reportes")
    public ResponseEntity<ReporteDTO> agregarReporte(@Valid @RequestBody ReporteDTO dto) {
        logger.info("Reporte a agregar" + dto);
        ReporteDTO respuesta = reporteServicio.guardarReporte(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idReporte}")
                .buildAndExpand(respuesta.getIdReporte())
                .toUri();
        return ResponseEntity.created(ubicacion).body(respuesta);
    }

    // Controlador para utilizar el metodo de buscar un Reporte por id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reportes/{idReporte}")
    public ResponseEntity<ReporteDTO> obtenerReporteId(
            @PathVariable int idReporte) {
        ReporteDTO reporteDTO = this.reporteServicio.buscarReporteId(idReporte);
        return ResponseEntity.ok(reporteDTO);
    }

    // Controlador para utilizar el metodo de elimninar un Reporte
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/reportes/{idReporte}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarReporte(@PathVariable int idReporte) {
        reporteServicio.eliminarReporte(idReporte);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de un Reporte
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/reportes/{idReporte}")
    public ResponseEntity<ReporteDTO> actualizarReporte(
            @PathVariable int idReporte,
            @Valid @RequestBody ReporteDTO dto) {
        ReporteDTO actualizado = reporteServicio.actualizarReporte(idReporte, dto);
        return ResponseEntity.ok(actualizado);
    }
}
