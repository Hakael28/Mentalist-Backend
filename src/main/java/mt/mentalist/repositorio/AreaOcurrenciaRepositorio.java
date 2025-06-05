
package mt.mentalist.repositorio;


import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.AreaOcurrencia;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaOcurrenciaRepositorio  extends JpaRepository<AreaOcurrencia, Integer>{
    Optional<AreaOcurrencia> findTopByOrderByIdAreaOcurrenciaDesc();
}