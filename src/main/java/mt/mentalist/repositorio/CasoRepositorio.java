
package mt.mentalist.repositorio;

import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.Caso;
import org.springframework.stereotype.Repository;

@Repository
public interface CasoRepositorio extends JpaRepository<Caso, Integer>{
}
