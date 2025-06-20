package mt.mentalist.controlador.Basicos;

import jakarta.validation.Valid;
import mt.mentalist.DTO.Basicas.PacienteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.servicio.Basicos.PacienteServicio;
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
public class PacienteControlador {
    private static final Logger logger = LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private PacienteServicio pacienteServicio;

    // Controlador para utilizar el metodo de listar los pacientes
    //http://localhost:8084/mentalist-web/basicos/pacientes
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/pacientes")
    public List<PacienteDTO> obtenerPacientes() {
        List<PacienteDTO> pacientes = this.pacienteServicio.listarPacientes();
        logger.info("Reportes obtenidos:");
        pacientes.forEach((paciente -> logger.info(paciente.toString())));
        return pacientes;
    }

    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/pacientes")
    public ResponseEntity<PacienteDTO> agregarPaciente(@Valid @RequestBody PacienteDTO dto) {
        logger.info("Paciente a agregar" + dto);

        PacienteDTO pacienteGuardado = this.pacienteServicio.guardarPaciente(dto);
        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idReporte}")
                .buildAndExpand(pacienteGuardado.getIdPaciente())
                .toUri();
        return ResponseEntity.created(ubicacion).body(pacienteGuardado);
    }

    // Controlador para utilizar el metodo de buscar pacientes por id
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<PacienteDTO> obtenerPacienteId(
            @PathVariable int idPaciente) {
        PacienteDTO paciente = this.pacienteServicio.buscarPacientesId(idPaciente);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el paciente con el id: " + idPaciente);
        }
    }

    // Controlador para utilizar el metodo de eliminar paciente
    @PreAuthorize("hasRole('MEDICO')")
    @DeleteMapping("/pacientes/{idPaciente}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarPaciente(@PathVariable int idPaciente) {
        pacienteServicio.eliminarPaciente(idPaciente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    // Controlador para utilizar el metodo de actualizar la informacion de los pacientes
    @PreAuthorize("hasRole('MEDICO')")
    @PutMapping("/pacientes/{idPaciente}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(
            @PathVariable int idPaciente,
            @Valid @RequestBody PacienteDTO dto) {
        PacienteDTO actualizado = pacienteServicio.actualizarPaciente(idPaciente, dto);
        return ResponseEntity.ok(actualizado);
    }
}


