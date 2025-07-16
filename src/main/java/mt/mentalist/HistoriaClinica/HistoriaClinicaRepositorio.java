package mt.mentalist.HistoriaClinica;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepositorio extends JpaRepository<HistoriaClinica, Integer> {
    List<HistoriaClinica> findByPaciente_IdPaciente(Integer idPaciente);

    Optional<HistoriaClinica> findTopByOrderByIdHistorialClinicaDesc();

}
