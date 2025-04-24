package mt.mentalist.controlador;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mt.mentalist.DTO.CursoVidaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.CursoVida;
import mt.mentalist.modelo.Paciente;
import mt.mentalist.servicio.CursoVidaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
//http://localhost:8084/mentalist-web
@RequestMapping("mantalist-web")
@CrossOrigin(value = "http://localhost:4200")

public class cursoVidaControlador {

    @Autowired
    private CursoVidaServicio cursoVidaServicio;


    // Controlador para utilizar el metodo de listar cursoVida
//http://localhost:8084/mentalist-web/cursoVida

    @GetMapping("/cursoVida")
    public List<CursoVida> obtenerCursoVida() {
        List<CursoVida> cursos = this.cursoVidaServicio.listarCursoVida();
        log.info("cursoVida obtenidos");
        cursos.forEach((curso -> log.info(curso.toString())));
        return cursos;

    }

    // Controlador para utilizar el metodo de guardar cursoVida
    @PostMapping("/cursoVida")
    public ResponseEntity<CursoVida> agregarCursoVida(@Valid @RequestBody CursoVidaDTO dto) {
        log.info("CursVida a agregar" + dto);
        CursoVida cursoVidaGuardado = this.cursoVidaServicio.guardarCursoVida(dto);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCursoVida}").buildAndExpand(cursoVidaGuardado.getIdCursoVida()).toUri();
        return ResponseEntity.created(ubicacion).body(cursoVidaGuardado);
    }

    // Controlador para utilizar el metodo de buscar curso vida por id
    @GetMapping("/cursoVida/{idCursoVida}")
    public ResponseEntity<CursoVida> obtenerCursoVidaId(
            @PathVariable int idCursoVida) {
        CursoVida cursoVida = this.cursoVidaServicio.buscarCursoVidaId(idCursoVida);
        if (cursoVida != null) {
            return ResponseEntity.ok(cursoVida);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el paciente con el id: " + idCursoVida);
        }
    }

    // Controlador para utilizar el metodo de eliminar curso vida
    @DeleteMapping("/cursoVida/{idCursoVida}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarCursoVida(@PathVariable int idCursoVida) {
        cursoVidaServicio.eliminarCursiVida(idCursoVida);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
