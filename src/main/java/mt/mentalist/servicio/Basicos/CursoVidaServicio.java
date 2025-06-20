package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.Basicas.CursoVidaDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
import mt.mentalist.modelo.Entidades.CursoVida;
import mt.mentalist.repositorio.CursoVidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoVidaServicio implements ICursoVidaServicio {

    @Autowired
    private CursoVidaRepositorio cursoVidaRepositorio;

    @Override
    public List<CursoVidaDTO> listarCursoVida() {
        List<CursoVida> cursoVidas = cursoVidaRepositorio.findAll();
        return cursoVidas.stream()
                .map(this::convertirEntidadDTO)
                .toList();
    }

    @Override
    public Optional<CursoVida> findTopByOrderByIdCursoVidaDesc() {
        return cursoVidaRepositorio.findTopByOrderByIdCursoVidaDesc();
    }


    @Override
    public CursoVidaDTO buscarCursoVidaId(Integer idCursoVida) {
        CursoVida cursoVida = cursoVidaRepositorio.findById(idCursoVida)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el curso de vida con el ID : "+ idCursoVida));
        return convertirEntidadDTO(cursoVida);
    }

    @Override
    public CursoVidaDTO guardarCursoVida(CursoVidaDTO dto) {
        CursoVida cursoVida = new CursoVida();
        cursoVida.setEtapa(dto.getEtapa());
        CursoVida cursoVidaGuardado = cursoVidaRepositorio.save(cursoVida);
        return convertirEntidadDTO(cursoVidaGuardado);
    }

    @Override
    public void eliminarCursoVida(Integer idCursoVida) {
        Optional<CursoVida> cursoVida = cursoVidaRepositorio.findById(idCursoVida);
        if(cursoVida.isPresent()){
            cursoVidaRepositorio.deleteById(idCursoVida);
        }else {
            throw new RuntimeException("Usuario no encontrado con ID: " +idCursoVida);
        }
    }
    private CursoVidaDTO convertirEntidadDTO(CursoVida cursoVida){
        CursoVidaDTO dto = new CursoVidaDTO();
        dto.setIdCursoVida(cursoVida.getIdCursoVida());
        dto.setEtapa(cursoVida.getEtapa());
        return  dto;
    }
}
