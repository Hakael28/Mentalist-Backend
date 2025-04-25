/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.servicio;

import mt.mentalist.DTO.ReporteDTO;
import mt.mentalist.modelo.Reporte;

import java.util.List;
import java.util.Optional;

public interface IReporteServicio {
    // Metodo para listar los reportes
    public List<Reporte> listarReportes();

    // Metodo para seleccionar el ultimo  registro reporte

    public Optional<Reporte> findTopByOrderByIdReporteDesc();

    //Metodo para busacar reporte 
    public Reporte buscarReporteId(Integer id_reporte);

    //Metodo para guardar reporte
    public Reporte guardarReporte(ReporteDTO dto);
    public Reporte actualizarReporte(Reporte existente, ReporteDTO dto);

    //Metodo para eliminar reporte
    public void eliminarReporte(Integer idReporte);
}
