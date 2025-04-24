/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

//    // Verificar si el usuario existe
//    boolean existsByUsuario(String usuario);
    Optional<Usuario>findTopByOrderByIdUsuarioDesc();
//
//    // Buscar usuario sin distinguir mayúsculas y minúsculas
//    @Query("SELECT u FROM Usuario u WHERE LOWER(TRIM(u.usuario)) = LOWER(TRIM(:usuario))")
//    Optional<Usuario> findByUsuario(String usuario);
}
