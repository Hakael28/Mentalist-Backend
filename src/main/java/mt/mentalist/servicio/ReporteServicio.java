/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.ReporteDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.modelo.Usuario;
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

        Usuario usuario = usuarioServicio.buscarUsuarioId(dto.getIdUsuario());
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el ID: " + dto.getIdUsuario());
        }

        Reporte reporte = new Reporte();

        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setFecha(dto.getFecha());
        Reporte reporteGuardado = reporteRepositorio.save(reporte);
        return reporteGuardado;
    }

    @Override
    public Reporte actualizarReporte(Reporte exitente, ReporteDTO dto) {
        Usuario usuario = usuarioServicio.buscarUsuarioId(dto.getIdUsuario());
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No ser encontro el usuario con el id: " + dto.getIdUsuario());
        }
        exitente.setUsuario(usuario);
        exitente.setTipoReporte(dto.getTipoReporte());
        exitente.setDescripcion(dto.getDescripcion());
        exitente.setFecha(dto.getFecha());
        return reporteRepositorio.save(exitente);
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
