
package mt.mentalist.repositorio;
import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.RutaAtencion;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutaAtencionRepositorio extends  JpaRepository<RutaAtencion, Integer>{
    Optional<RutaAtencion> findTopByOrderByIdRutaAtencionDesc();
}
