
package mt.mentalist.Eapb;

import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EapbRepositorio extends JpaRepository<Eapb, Integer>{
    Optional<Eapb> findTopByOrderByIdEapbDesc();

}
