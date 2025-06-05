package mt.mentalist.controlador.Basicos;

import jakarta.validation.Valid;
import mt.mentalist.DTO.DTOBasics.EapbDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Entidades.Eapb;
import mt.mentalist.servicio.Basicos.EapbServicio;
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
public class EapbControlador {
    @Autowired
    private EapbServicio eapbServicio;
    private static final Logger logger =
            LoggerFactory.getLogger(EapbControlador.class);
    private Eapb Eapb;


    // Controlador para utilizar el metodo de listar Eapb
    //http://localhost:8084/mentalist-web/basicos/Eapb
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/eapbs")
    public List<EapbDTO> obtenerEapb() {
        List<EapbDTO> eapbs = this.eapbServicio.listarEapb();
        logger.info("Eapb obtenidos");
        eapbs.forEach((eapb -> logger.info(eapb.toString())));
        return eapbs;
    }

    // Controlador para utilizar el metodo de guardar Eapb
    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/eapbs")
    public ResponseEntity<EapbDTO> agregarEapb(@Valid @RequestBody EapbDTO dto) {
        logger.info("Eapb a agregar" + dto);
        EapbDTO eapbGuardado = this.eapbServicio.guardarEapb(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idEapb}")
                .buildAndExpand(eapbGuardado.getIdEapb())
                .toUri();
        return ResponseEntity.created(ubicacion).body(eapbGuardado);
    }

    // Controlador para utilizar el metodo de buscar Eapb por id
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/eapbs/{idEapb}")
    public ResponseEntity<EapbDTO> obtenerEapbid(
            @PathVariable int idEapb) {
        EapbDTO eapb = this.eapbServicio.buscarEapbId(idEapb);
        if (eapb != null) {
            return ResponseEntity.ok(eapb);
        } else {
            throw new RecursoNoEncontradoExcepcion("no se encontro Eapb con el id:" + idEapb);
        }
    }

    // Controlador para utilizar el metodo de eliminar Eapb
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/eapbs/{idEapb}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarEapb(@PathVariable int idEapb) {
        eapbServicio.eliminarEapb(idEapb);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

