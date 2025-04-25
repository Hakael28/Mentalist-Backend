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
    public List<Paciente> listarPacientes() {
        return pacienteRepositorio.findAll();
    }

    // Buscar un paciente por su ID
    @Override
    public Paciente buscarPacientesId(Integer idPaciente) {
        return pacienteRepositorio.findById(idPaciente).orElse(null);
    }

    // Guardar un paciente en la base de datos
    @Override
    public Paciente guardarPaciente(PacienteDTO dto) {
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

        return pacienteRepositorio.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Integer idPaciente, PacienteDTO dto) {
        Paciente existente = buscarPacientesId(idPaciente);
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

        return pacienteRepositorio.save(existente);
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


}
