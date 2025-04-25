package mt.mentalist.controlador;

import jakarta.validation.Valid;
import mt.mentalist.DTO.PacienteDTO;
import mt.mentalist.DTO.ReporteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Paciente;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.servicio.PacienteServicio;
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
public class PacienteControlador {
    private static final Logger logger = LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private PacienteServicio pacienteServicio;

    // Controlador para utilizar el metodo de listar los pacientes
    //http://localhost:8084/mentalist-web/pacientes
    @GetMapping("/pacientes")
    public List<Paciente> obtenerPacientes() {
        List<Paciente> pacientes = this.pacienteServicio.listarPacientes();
        logger.info("Reportes obtenidos:");
        pacientes.forEach((paciente -> logger.info(paciente.toString())));
        return pacientes;
    }

    // Controlador para utilizar el metodo de guardar pacientes
    @PostMapping("/pacientes")
    public ResponseEntity<Paciente> agregarPaciente(@Valid @RequestBody PacienteDTO dto) {
        logger.info("Reporte a agregar" + dto);

        Paciente pacienteGuardado = this.pacienteServicio.guardarPaciente(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idReporte}")
                .buildAndExpand(pacienteGuardado.getIdPaciente())
                .toUri();
        return ResponseEntity.created(ubicacion).body(pacienteGuardado);
    }

    // Controlador para utilizar el metodo de buscar pacientes por id
    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<Paciente> obtenerPacienteId(
            @PathVariable int idPaciente) {
        Paciente paciente = this.pacienteServicio.buscarPacientesId(idPaciente);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el paciente con el id: " + idPaciente);
        }
    }

    // Controlador para utilizar el metodo de eliminar paciente
    @DeleteMapping("/pacientes/{idPaciente}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarPaciente(@PathVariable int idPaciente) {
        pacienteServicio.eliminarPaciente(idPaciente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de los pacientes
    @PutMapping("/pacientes/{idPaciente}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable int idPaciente,
            @Valid @RequestBody PacienteDTO dto) {
        Paciente actualizado = pacienteServicio.actualizarPaciente(idPaciente, dto);
        return ResponseEntity.ok(actualizado);
    }
}


