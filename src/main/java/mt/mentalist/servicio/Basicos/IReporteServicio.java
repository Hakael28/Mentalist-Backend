/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.servicio.Basicos;

import mt.mentalist.DTO.Basicas.ReporteDTO;

import java.util.List;

public interface IReporteServicio {
    // Metodo para listar los reportes
    List<ReporteDTO> listarReportes();

    //Metodo para buscar reporte
    ReporteDTO buscarReporteId(Integer idReporte);

    //Metodo para guardar reporte
    ReporteDTO guardarReporte(ReporteDTO dto);
    //Metodo para actualizar reporte
    ReporteDTO actualizarReporte(Integer idReporte, ReporteDTO dto);

    //Metodo para eliminar reporte
    void eliminarReporte(Integer idReporte);
}
