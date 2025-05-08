
package mt.mentalist.repositorio;

import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Caso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CasoRepositorio extends JpaRepository<Caso, Integer>{
}
