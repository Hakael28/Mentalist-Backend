/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.servicio.Basicos;

import mt.mentalist.DTO.DTOBasics.PacienteDTO;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IPacienteServicio {

    // Metodo para listar los pacientes
    List<PacienteDTO> listarPacientes();

    //Metodo para busacar paciente
    PacienteDTO buscarPacientesId(Integer idPaciente);

    //Metodo para guardar paciente
    PacienteDTO guardarPaciente(PacienteDTO dto);

    PacienteDTO actualizarPaciente(Integer idPaciente, PacienteDTO dto);

    //Metodo para eliminar paciente
    void eliminarPaciente(Integer idPaciente);
}

