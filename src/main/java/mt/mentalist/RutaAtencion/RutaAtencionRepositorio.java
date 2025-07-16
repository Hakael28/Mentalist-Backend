
package mt.mentalist.RutaAtencion;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutaAtencionRepositorio extends  JpaRepository<RutaAtencion, Integer>{
    Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc();
}
