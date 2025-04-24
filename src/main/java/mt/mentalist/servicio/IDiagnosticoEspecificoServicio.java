/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.servicio;

import mt.mentalist.DTO.DiagnosticoEspecificoDTO;
import mt.mentalist.modelo.DiagnosticoEspecifico;
import mt.mentalist.modelo.Usuario;

import java.util.List;
import java.util.Optional;


public interface IDiagnosticoEspecificoServicio {
    // Metodo para listar los Diagnostico especificos
    public List<DiagnosticoEspecifico> listarDiagnosticoEspecifico();

    // Metodo para seleccionar el ultimo  registro de diagnostico especifico
    public Optional<DiagnosticoEspecifico> findTopByOrderByIdDiagnosticoEspecificoDesc();

    //Metodo para buscar Diagnostico especificos
    public DiagnosticoEspecifico buscarDiagnosticoEspecificoId(Integer id_diagnostico_especifico);

    //Metodo para guardar Diagnostico especificos
    public DiagnosticoEspecifico guardarDiagnosticoEspecifico(DiagnosticoEspecificoDTO dto);

    //Metodo para eliminar Diagnostico especificos
    public void eliminarDiagnosticoEspecifico(Integer idDiagnosticoEspecifico);
}
