/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.RutaAtencionDTO;
import mt.mentalist.modelo.RutaAtencion;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.RutaAtencionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaAtencionServicio implements IRutaAtencionServicio {

    @Autowired
    private RutaAtencionRepositorio rutaRepositorio;


    @Override
    public List<RutaAtencion> listarRutas() {
        List<RutaAtencion> rutas = rutaRepositorio.findAll();
        return rutas;
    }

    @Override
    public Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc() {
        return rutaRepositorio.findTopByOrderByIdRutaAtencionDesc();
    }

    @Override
    public RutaAtencion buscarRutaId(Integer idRutaAtencion) {
        RutaAtencion ruta = rutaRepositorio.findById(idRutaAtencion).orElse(null);
        return ruta;
    }

    @Override
    public RutaAtencion guardarRuta(RutaAtencionDTO dto) {
        RutaAtencion ruta = new RutaAtencion();
        ruta.setDescripcion(dto.getDescripcion());
        return rutaRepositorio.save(ruta);
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

}
