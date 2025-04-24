/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.ReporteDTO;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.repositorio.ReporteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteServicio implements IReporteServicio {

    @Autowired
    private ReporteRepositorio reporteRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public List<Reporte> listarReportes() {
        List<Reporte> reportes = reporteRepositorio.findAll();
        return reportes;
    }
    // Metodo para seleccionar el ultimo  registro de reporte

    @Override
    public Optional<Reporte> findTopByOrderByIdReporteDesc() {
        return reporteRepositorio.findTopByOrderByIdReporteDesc();
    }

    @Override
    public Reporte buscarReporteId(Integer id_reporte) {
        Reporte reporte = reporteRepositorio.findById(id_reporte).orElse(null);
        return reporte;
    }

    @Override
    public Reporte guardarReporte(ReporteDTO dto) {
        Reporte reporte = new Reporte();

        reporte.setUsuario(usuarioServicio.buscarUsuarioId(dto.getIdUsuario()));

        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setFecha(dto.getFecha());
        Reporte reporteGuardado = reporteRepositorio.save(reporte);
        return reporteGuardado;
    }

    @Override
    public void eliminarReporte(Integer idReporte) {
        Optional<Reporte> reporte = reporteRepositorio.findById(idReporte);
        if (reporte.isPresent()) {
            reporteRepositorio.deleteById(idReporte);
        } else {
            throw new RuntimeException("Reporte no encontrado con ID: " + idReporte);
        }

    }

}
