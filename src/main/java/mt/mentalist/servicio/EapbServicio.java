package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;


import mt.mentalist.DTO.EapbDTO;
import mt.mentalist.modelo.Eapb;
import mt.mentalist.modelo.Reporte;
import mt.mentalist.repositorio.EapbRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EapbServicio implements IEapbServicio {
    @Autowired
    private EapbRepositorio eapbRepositorio;

    @Override
    public List<Eapb> listarEapb() {
        List<Eapb> eapb = eapbRepositorio.findAll();
        return eapb;

    }

    @Override
    public Optional<Eapb> findTopByOrderByIdEapbDesc() {

        return eapbRepositorio.findTopByOrderByIdEapbDesc();
    }

    @Override
    public Eapb buscarEapbId(Integer idEapb) {
        Eapb eapb = eapbRepositorio.findById(idEapb).orElse(null);
        return eapb;

    }

    @Override
    public Eapb guardarEapb(EapbDTO dto) {
        Eapb eapb = new Eapb();
        eapb.setNombre(dto.getNombre());
        Eapb eapbGuardada = eapbRepositorio.save(eapb);
        return eapbGuardada;
    }

    @Override
    public void eliminarEapb(Integer idEapb) {
        Optional<Eapb> eapb = eapbRepositorio.findById(idEapb);
        if(eapb.isPresent()){
            eapbRepositorio.deleteById(idEapb);
        }else {
            throw new RuntimeException("EAPB no encontrada con ID: " +idEapb);
        }

    }


}
