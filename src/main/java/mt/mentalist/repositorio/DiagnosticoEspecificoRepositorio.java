package mt.mentalist.repositorio;


import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.DiagnosticoEspecifico;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticoEspecificoRepositorio extends JpaRepository<DiagnosticoEspecifico, Integer>{
     Optional<DiagnosticoEspecifico> findTopByOrderByIdDiagnosticoEspecificoDesc();

}