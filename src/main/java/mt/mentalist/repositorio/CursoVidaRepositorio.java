
package mt.mentalist.repositorio;


import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.CursoVida;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoVidaRepositorio extends JpaRepository<CursoVida, Integer>{
    Optional<CursoVida> findTopByOrderByIdCursoVidaDesc();
}
    

