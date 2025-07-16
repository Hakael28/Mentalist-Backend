package mt.mentalist.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    // Buscar usuario sin distinguir mayúsculas y minúsculas
    @Query("SELECT u FROM Usuario u WHERE LOWER(TRIM(u.usuario)) = LOWER(TRIM(:usuario))")
    Optional<Usuario> findByUsuario(String usuario);
}
