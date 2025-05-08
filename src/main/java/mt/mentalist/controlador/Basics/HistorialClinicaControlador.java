package mt.mentalist.controlador.Basics;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.HistoriaClinicaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.servicio.Basicos.HistoriaClinicaServicio;
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
@RequestMapping("mantalist-web")
@CrossOrigin(value = "http://localhost:4200")
public class HistorialClinicaControlador {
    @Autowired
    private HistoriaClinicaServicio historiaClinicaServicio;
    private static final Logger logger =
            LoggerFactory.getLogger(HistorialClinicaControlador.class);

    // Controlador para utilizar el metodo de listar Historia clinica
    //http://localhost:8084/mentalist-web/Historia clinica
    @GetMapping ("/historias")
    public List<HistoriaClinicaDTO> obtenerhistorias() {
        List<HistoriaClinicaDTO> historias = this.historiaClinicaServicio.listarClinica();
        logger.info("Histarias obtrenidas");
        historias.forEach((historia -> logger.info(historia.toString())));
        return historias;
    }

    // Controlador para utilizar el metodo de guardarHistoriaClinica
    @PostMapping("/historias")
    public ResponseEntity<HistoriaClinicaDTO> agregarHistoriaClinica(@Valid @RequestBody HistoriaClinicaDTO dto) {
        logger.info("HistoriaClinica a agregar" + dto);
        HistoriaClinicaDTO historiaClinicaGuardada = this.historiaClinicaServicio.guardarClinica(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idHistorialClinica}")
                .buildAndExpand(historiaClinicaGuardada.getIdHistorialClinica())
                .toUri();
        return ResponseEntity.created(ubicacion).body(historiaClinicaGuardada);
    }
    // Controlador para utilizar el metodo de buscar Historia  Clinica por id
    @GetMapping("/historias/{idHistorialClinica}")
    public ResponseEntity<HistoriaClinicaDTO> obtenerhHistoriaClinicaid(
            @PathVariable int idHistorialClinica) {
        HistoriaClinicaDTO historiaClinica = this.historiaClinicaServicio.buscarClinicaId(idHistorialClinica);
        if (historiaClinica != null) {
            return ResponseEntity.ok(historiaClinica);
        } else {
            throw new RecursoNoEncontradoExcepcion("no se encontro Historia Clinica con el id:" + idHistorialClinica);
        }
    }

    // Controlador para utilizar el metodo de eliminar Historias Clinica
    @DeleteMapping("/historias/{idHistorialClinica}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarhistorias(@PathVariable int idHistorialClinica) {
        historiaClinicaServicio.eliminarClinica(idHistorialClinica);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }



}



