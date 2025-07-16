package mt.mentalist.DiagnosticoEspecifico;


import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticoEspecificoRepositorio extends JpaRepository<DiagnosticoEspecifico, Integer>{
     Optional<DiagnosticoEspecifico> findTopByOrderByIdDiagnosticoEspecificoDesc();

}