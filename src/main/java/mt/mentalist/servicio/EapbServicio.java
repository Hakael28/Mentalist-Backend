package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;


import mt.mentalist.DTO.EapbDTO;
import mt.mentalist.exception.RecursoNoEncontradoExcepcion;
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
    public List<EapbDTO> listarEapb() {
        List<Eapb> eapbs = eapbRepositorio.findAll();
        return eapbs.stream()
                .map(this::convertirEntidadDTO)
                .toList();

    }

    @Override
    public Optional<Eapb> findTopByOrderByIdEapbDesc() {

        return eapbRepositorio.findTopByOrderByIdEapbDesc();
    }

    @Override
    public EapbDTO buscarEapbId(Integer idEapb) {
        Eapb eapb = eapbRepositorio.findById(idEapb)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro el Eapb con el ID : "+ idEapb));
        return convertirEntidadDTO(eapb);

    }

    @Override
    public EapbDTO guardarEapb(EapbDTO dto) {
        Eapb eapb = new Eapb();
        eapb.setNombre(dto.getNombre());
        Eapb eapbGuardada = eapbRepositorio.save(eapb);
        return  convertirEntidadDTO(eapbGuardada);
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

    private EapbDTO convertirEntidadDTO (Eapb eapb){
        EapbDTO dto = new EapbDTO();
        dto.setIdEapb(eapb.getIdEapb());
        dto.setNombre(eapb.getNombre());
        return dto;
    }


}
