/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.PacienteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Paciente;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        paciente.setNombreCompleto(dto.getNombreCompleto());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setEdad(dto.getEdad());
        paciente.setGenero(dto.getGenero());
        paciente.setNacionalidad(dto.getNacionalidad());
        paciente.setTelefono(dto.getTelefono());
        paciente.setCorreo(dto.getCorreo());
        paciente.setDireccion(dto.getDireccion());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());

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
        existente.setNombreCompleto(dto.getNombreCompleto());
        existente.setFechaNacimiento(dto.getFechaNacimiento());
        existente.setEdad(dto.getEdad());
        existente.setGenero(dto.getGenero());
        existente.setNacionalidad(dto.getNacionalidad());
        existente.setTelefono(dto.getTelefono());
        existente.setCorreo(dto.getCorreo());
        existente.setDireccion(dto.getDireccion());
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
        dto.setNombreCompleto(paciente.getNombreCompleto());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setEdad(paciente.getEdad());
        dto.setGenero(paciente.getGenero());
        dto.setNacionalidad(paciente.getNacionalidad());
        dto.setTelefono(paciente.getTelefono());
        dto.setCorreo(paciente.getCorreo());
        dto.setDireccion(paciente.getDireccion());
        return  dto;
    }
    private Paciente obtenerPacienteEntidad(Integer idPaciente){
        return pacienteRepositorio.findById(idPaciente).orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el paciente con el ID: "+ idPaciente));
    }


}
