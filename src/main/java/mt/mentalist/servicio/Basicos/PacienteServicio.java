package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DTOBasics.PacienteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Paciente;
import mt.mentalist.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mt.mentalist.Funciones.Encriptacion.Encriptacion;
@Service
public class PacienteServicio implements IPacienteServicio {
    @Autowired
    private PacienteRepositorio pacienteRepositorio;


    // Listar todos los pacientes
    @Override
    public List<PacienteDTO> listarPacientes() {
       List<Paciente> pacientes = pacienteRepositorio.findAll();
       return pacientes.stream()
               .map(this::convertirEntidadDTO)
               .toList();
    }

    // Buscar un paciente por su ID
    @Override
    public PacienteDTO buscarPacientesId(Integer idPaciente) {
        Paciente paciente = pacienteRepositorio.findById(idPaciente)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontro el paciente con el ID: " +idPaciente ));
        return convertirEntidadDTO(paciente);
    }

    // Guardar un paciente en la base de datos
    @Override
    public PacienteDTO guardarPaciente(PacienteDTO dto) {
        Paciente paciente = new Paciente();

        paciente.setIdPaciente(dto.getIdPaciente());
        paciente.setTipoDocumento(dto.getTipoDocumento());
        paciente.setNombreCompleto(Encriptacion.encriptarTexto(dto.getNombreCompleto()));
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setEdad(dto.getEdad());
        paciente.setGenero(dto.getGenero());
        paciente.setNacionalidad(Encriptacion.encriptarTexto(dto.getNacionalidad()));
        paciente.setTelefono(Encriptacion.encriptarTexto(dto.getTelefono()));
        paciente.setCorreo(Encriptacion.encriptarTexto(dto.getCorreo()));
        paciente.setDireccion(Encriptacion.encriptarTexto(dto.getDireccion()));
        Paciente pacienteGuardado = pacienteRepositorio.save(paciente);
        return convertirEntidadDTO(pacienteGuardado);
    }

    @Override
    public PacienteDTO actualizarPaciente(Integer idPaciente, PacienteDTO dto) {
        Paciente existente = obtenerPacienteEntidad(idPaciente);
        if (existente == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el paciente con el ID: " + idPaciente);
        }
        existente.setTipoDocumento(dto.getTipoDocumento());
        existente.setNombreCompleto(Encriptacion.encriptarTexto(dto.getNombreCompleto()));
        existente.setFechaNacimiento(dto.getFechaNacimiento());
        existente.setEdad(dto.getEdad());
        existente.setGenero(dto.getGenero());
        existente.setNacionalidad(Encriptacion.encriptarTexto(dto.getNacionalidad()));
        existente.setTelefono(Encriptacion.encriptarTexto(dto.getTelefono()));
        existente.setCorreo(Encriptacion.encriptarTexto(dto.getCorreo()));
        existente.setDireccion(Encriptacion.encriptarTexto(dto.getDireccion()));
        Paciente actualizado = pacienteRepositorio.save(existente);
        return convertirEntidadDTO(actualizado);
    }


    // Eliminar un paciente
    @Override
    public void eliminarPaciente(Integer idPaciente) {
        Optional<Paciente> paciente = pacienteRepositorio.findById(idPaciente);
        if (paciente.isPresent()) {
            pacienteRepositorio.deleteById(idPaciente);
        } else {
            throw new RuntimeException("Paciente no encontrado con ID: " + idPaciente);
        }
    }

    private PacienteDTO convertirEntidadDTO(Paciente paciente){
        PacienteDTO dto = new PacienteDTO();
        dto.setIdPaciente(paciente.getIdPaciente());
        dto.setTipoDocumento(paciente.getTipoDocumento());
        dto.setNombreCompleto(Encriptacion.desencriptarTexto(paciente.getNombreCompleto()));
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setEdad(paciente.getEdad());
        dto.setGenero(paciente.getGenero());
        dto.setNacionalidad(Encriptacion.desencriptarTexto(paciente.getNacionalidad()));
        dto.setTelefono(Encriptacion.desencriptarTexto(paciente.getTelefono()));
        dto.setCorreo(Encriptacion.desencriptarTexto(paciente.getCorreo()));
        dto.setDireccion(Encriptacion.desencriptarTexto(paciente.getDireccion()));
        return  dto;
    }
    public Paciente obtenerPacienteEntidad(Integer idPaciente){
        return pacienteRepositorio.findById(idPaciente).orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el paciente con el ID: "+ idPaciente));
    }
}
