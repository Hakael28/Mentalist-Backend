package mt.mentalist.controlador.Basics;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.AreaOcurrenciaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.servicio.Basicos.AreaOcurrenciaServicio;
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
public class AreaOcurrenciaControlador {
    @Autowired
    private AreaOcurrenciaServicio areaOcurrenciaServicio;
    private static final Logger logger =
            LoggerFactory.getLogger(AreaOcurrenciaControlador.class);

    // Controlador para utilizar el metodo de listar areas de ocurrencia
    //http://localhost:8084/mentalist-web/cursoVida
    @GetMapping("/areas")
    public List<AreaOcurrenciaDTO> obtenerAreasOcurrencia() {
        List<AreaOcurrenciaDTO> areas = this.areaOcurrenciaServicio.listarAreaOcureencia();
        logger.info("Areas de ocurrencia obtenidas");
        areas.forEach((area -> logger.info(area.toString())));
        return areas;

    }

    // Controlador para utilizar el metodo de guardar cursoVida
    @PostMapping("/areas")
    public ResponseEntity<AreaOcurrenciaDTO> agregarAreaOcurrencia(@Valid @RequestBody AreaOcurrenciaDTO dto) {
        logger.info("Area de ocurrencia a agregar" + dto);
        AreaOcurrenciaDTO areaGuardada = this.areaOcurrenciaServicio.guardarAreaOcurrencia(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idCursoVida}")
                .buildAndExpand(areaGuardada.getIdAreaOcurrencia())
                .toUri();
        return ResponseEntity.created(ubicacion).body(areaGuardada);
    }

    // Controlador para utilizar el metodo de buscar area de ocurrencia por id
    @GetMapping("/areas/{idAreaOcurrencia}")
    public ResponseEntity<AreaOcurrenciaDTO> obtenerAreaOcurrenciaId(
            @PathVariable int idAreaOcurrencia) {
        AreaOcurrenciaDTO areaOcurrencia = this.areaOcurrenciaServicio.buscarAreaOcurrencaId(idAreaOcurrencia);
        if (areaOcurrencia != null) {
            return ResponseEntity.ok(areaOcurrencia);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el paciente con el id: " + idAreaOcurrencia);
        }
    }

    // Controlador para utilizar el metodo de eliminar curso vida
    @DeleteMapping("/areas/{idAreaOcurrencia}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarAreaOcurrencia(@PathVariable int idAreaOcurrencia) {
        areaOcurrenciaServicio.eliminarAreaOcurrencia(idAreaOcurrencia);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
