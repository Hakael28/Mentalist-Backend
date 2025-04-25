package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.EapbDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Eapb;
import mt.mentalist.servicio.EapbServicio;
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
public class EapbControlador {
    @Autowired
    private EapbServicio eapbServicio;
    private static final Logger logger =
            LoggerFactory.getLogger(EapbControlador.class);
    private Eapb Eapb;


    // Controlador para utilizar el metodo de listar Eapb
    //http://localhost:8084/mentalist-web/Eapb
    @GetMapping("/eapbs")
    public List<Eapb> obtenerEapb() {
        List<Eapb> eapbs = this.eapbServicio.listarEapb();
        logger.info("Eapb obetenido");
        eapbs.forEach((eapb -> logger.info(eapb.toString())));
        return eapbs;
    }

    // Controlador para utilizar el metodo de guardar Eapb
    @PostMapping("/eapbs")
    public ResponseEntity<Eapb> agregarEapb(@Valid @RequestBody EapbDTO dto) {
        logger.info("Eapb a agregar" + dto);
        Eapb eapbGuardado = this.eapbServicio.guardarEapb(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idEapb}")
                .buildAndExpand(eapbGuardado.getIdEapb())
                .toUri();
        return ResponseEntity.created(ubicacion).body(eapbGuardado);
    }

    // Controlador para utilizar el metodo de buscar Eapb por id
    @GetMapping("/eapbs/{idEapb}")
    public ResponseEntity<Eapb> obtenerEapbid(
            @PathVariable int idEapb) {
        Eapb eapb = this.eapbServicio.buscarEapbId(idEapb);
        if (Eapb != null) {
            return ResponseEntity.ok(Eapb);
        } else {
            throw new RecursoNoEncontradoExcepcion("no se encontro Eapb con el id:" + idEapb);
        }
    }
    // Controlador para utilizar el metodo de eliminar Eapb
    @DeleteMapping("/eapbs/{idEapb}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarEapb(@PathVariable int idEapb) {
        eapbServicio.eliminarEapb(idEapb);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }



}

