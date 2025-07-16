/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.Reporte;

import java.util.List;
import java.util.Optional;

import mt.mentalist.Funciones.Seguridad.EncriptacionServicio;
import mt.mentalist.Global.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.Usuario.Usuario;
import mt.mentalist.Usuario.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteServicio implements IReporteServicio {

    @Autowired
    private ReporteRepositorio reporteRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private EncriptacionServicio encriptacionServicio;

    @Override
    public List<ReporteDTO> listarReportes() {
        List<Reporte> reportes = reporteRepositorio.findAll();
        return reportes.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }

    @Override
    public ReporteDTO buscarReporteId(Integer idReporte) {
        Reporte reporte = reporteRepositorio.findById(idReporte)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el reporte con el ID : "+ idReporte));
        return convertirEntidadDTO(reporte);
    }

    @Override
    public ReporteDTO guardarReporte(ReporteDTO dto) {

        Usuario usuario = usuarioServicio.ObtenerUsuarioEntidad(dto.getIdUsuario());
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el usuario con el ID: " + dto.getIdUsuario());
        }

        Reporte reporte = new Reporte();

        reporte.setUsuario(usuario);
        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setDescripcion(encriptacionServicio.encriptarTexto(dto.getDescripcion()));
        reporte.setFecha(dto.getFecha());
        Reporte reporteGuardado = reporteRepositorio.save(reporte);
        return convertirEntidadDTO(reporteGuardado);
    }

    @Override
    public ReporteDTO actualizarReporte(Integer idReporte, ReporteDTO dto) {
        Reporte existente = obtenerReporteEntidad(idReporte);
        if (existente == null) {
            throw new RecursoNoEncontradoExcepcion("No ser encontro el reporte con el id: " + idReporte);
        }
        Usuario usuario = usuarioServicio.ObtenerUsuarioEntidad(dto.getIdUsuario());
        if (usuario == null) {
            throw new RecursoNoEncontradoExcepcion("No ser encontro el usuario con el id: " + dto.getIdUsuario());
        }
        existente.setUsuario(usuario);
        existente.setTipoReporte(dto.getTipoReporte());
        existente.setDescripcion(encriptacionServicio.encriptarTexto(dto.getDescripcion()));
        existente.setFecha(dto.getFecha());
        Reporte actualizado = reporteRepositorio.save(existente);
        return convertirEntidadDTO(actualizado);
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

    private ReporteDTO convertirEntidadDTO(Reporte reporte){
        ReporteDTO dto = new ReporteDTO();
        dto.setIdReporte(reporte.getIdReporte());
        dto.setIdUsuario(reporte.getUsuario().getIdUsuario());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setDescripcion(encriptacionServicio.desencriptarTexto(reporte.getDescripcion()));
        dto.setFecha(reporte.getFecha());
        return dto;
    }

    private Reporte obtenerReporteEntidad(Integer idReporte) {
        return reporteRepositorio.findById(idReporte)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el reporte con el ID: " + idReporte));
    }
}
