/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DTOBasics.RutaAtencionDTO;
import mt.mentalist.servicio.Seguridad.Seguridad.EncriptacionServicio;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Entidades.RutaAtencion;
import mt.mentalist.repositorio.RutaAtencionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaAtencionServicio implements IRutaAtencionServicio {

    @Autowired
    private RutaAtencionRepositorio rutaRepositorio;
    @Autowired
    private EncriptacionServicio encriptacionServicio;


    @Override
    public List<RutaAtencionDTO> listarRutas() {
        List<RutaAtencion> rutas = rutaRepositorio.findAll();
        return rutas.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }

    @Override
    public Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc() {
        return rutaRepositorio.findTopByOrderByIdRutaAtencionDesc();
    }

    @Override
    public RutaAtencionDTO buscarRutaId(Integer idRutaAtencion) {
        RutaAtencion ruta = rutaRepositorio.findById(idRutaAtencion)
                .orElseThrow(()->new RecursoNoEncontradoExcepcion("No se encontro la Ruta de atencion con el ID: " + idRutaAtencion));
        return convertirEntidadDTO(ruta);
    }

    @Override
    public RutaAtencionDTO guardarRuta(RutaAtencionDTO dto) {
        RutaAtencion ruta = new RutaAtencion();
        ruta.setDescripcion(encriptacionServicio.encriptarTexto(dto.getDescripcion()));
        RutaAtencion rutaAtencionGuardada = rutaRepositorio.save(ruta);
        return convertirEntidadDTO(rutaAtencionGuardada);
    }

    @Override
    public void eliminarRuta(Integer idRutaAtencion) {
        Optional<RutaAtencion> ruta = rutaRepositorio.findById(idRutaAtencion);
        if(ruta.isPresent()){
            rutaRepositorio.deleteById(idRutaAtencion);
        }else {
            throw new RuntimeException("Ruta no encontrada con ID: " +idRutaAtencion);
        }
    }
    private RutaAtencionDTO convertirEntidadDTO(RutaAtencion rutaAtencion){
        RutaAtencionDTO dto = new RutaAtencionDTO();
        dto.setIdRutaAtencion(rutaAtencion.getIdRutaAtencion());
        dto.setDescripcion(encriptacionServicio.desencriptarTexto(rutaAtencion.getDescripcion()));
        return dto;
    }

}
