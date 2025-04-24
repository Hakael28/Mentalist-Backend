package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.CursoVidaDTO;
import mt.mentalist.modelo.CursoVida;
import mt.mentalist.modelo.Usuario;
import mt.mentalist.repositorio.CursoVidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoVidaServicio implements ICursoVidaServicio {

    @Autowired
    private CursoVidaRepositorio cursoVidaRepositorio;
    private Integer idCursoVida;


    @Override
    public List<CursoVida> listarCursoVida() {
        List<CursoVida> Cursovida = cursoVidaRepositorio.findAll();
        return Cursovida;
    }

    @Override
    public Optional<CursoVida> findTopByOrderByIdDesc() {
        return Optional.empty();
    }

    @Override
    public Optional<CursoVida> findTopByOrderByIdCursoVidaDesc() {
        return cursoVidaRepositorio.findTopByOrderByIdCursoVidaDesc();
    }


    @Override
    public CursoVida buscarCursoVidaId(Integer idCursoVida) {
        this.idCursoVida = idCursoVida;
        CursoVida cursovida = cursoVidaRepositorio.findById(idCursoVida).orElse(null);
        return cursovida;
    }

    @Override
    public CursoVida guardarCursoVida(CursoVidaDTO dto) {
        CursoVida cursovida = new CursoVida();
        cursovida.setEtapa(dto.getEtapa());
        CursoVida cursoVidaGuardada = cursoVidaRepositorio.save(cursovida);
        return cursoVidaGuardada;
    }

    @Override
    public void eliminarCursiVida(Integer idCursoVida) {
        Optional<CursoVida> cursoVida = cursoVidaRepositorio.findById(idCursoVida);
        if(cursoVida.isPresent()){
            cursoVidaRepositorio.deleteById(idCursoVida);
        }else {
            throw new RuntimeException("Usuario no encontrado con ID: " +idCursoVida);
        }
    }
}
