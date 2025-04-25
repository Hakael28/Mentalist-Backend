/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.servicio;
import mt.mentalist.DTO.PacienteDTO;
import mt.mentalist.modelo.Paciente;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface IPacienteServicio {
    
     // Metodo para listar los pacientes
    public List<Paciente>listarPacientes();
    
    //Metodo para busacar paciente
    public Paciente buscarPacientesId(Integer idPaciente);
    
    //Metodo para guardar paciente
    public Paciente guardarPaciente(PacienteDTO dto);

    public Paciente actualizarPaciente(Integer idPaciente, PacienteDTO dto);
    
    //Metodo para eliminar paciente
    public void eliminarPaciente(Integer idPaciente);    
}

