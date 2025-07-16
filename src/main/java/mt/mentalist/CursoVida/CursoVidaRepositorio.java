
package mt.mentalist.CursoVida;


import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoVidaRepositorio extends JpaRepository<CursoVida, Integer>{
    Optional<CursoVida> findTopByOrderByIdCursoVidaDesc();
}
    

