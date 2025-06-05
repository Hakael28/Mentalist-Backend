
package mt.mentalist.repositorio;

import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.Eapb;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EapbRepositorio extends JpaRepository<Eapb, Integer>{
    Optional<Eapb> findTopByOrderByIdEapbDesc();

}
