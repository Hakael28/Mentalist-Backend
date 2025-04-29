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
    public List<ReporteDTO> listarReportes();

    //Metodo para buscar reporte
    public ReporteDTO buscarReporteId(Integer idReporte);

    //Metodo para guardar reporte
    public ReporteDTO guardarReporte(ReporteDTO dto);
    //Metodo para actualizar reporte
    public ReporteDTO actualizarReporte(Integer idReporte, ReporteDTO dto);

    //Metodo para eliminar reporte
    public void eliminarReporte(Integer idReporte);
}
