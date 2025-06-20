
package mt.mentalist.repositorio;

import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Entidades.Caso;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface CasoRepositorio extends JpaRepository<Caso, Integer>{

    @Query("SELECT c.diagnostico.tipoDiagnostico AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE c.fechaNotificacion BETWEEN :desde AND :hasta " +
            "GROUP BY c.diagnostico.tipoDiagnostico")
    List<ConteoCategoria> contarCasosPorTipoDiagnostico(@Param("desde") LocalDate desde,
                                                        @Param("hasta") LocalDate hasta);

    @Query("SELECT c.cursoVida.etapa AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE c.fechaNotificacion BETWEEN :desde AND :hasta " +
            "GROUP BY c.cursoVida.etapa")
    List<ConteoCategoria> contarCasosPorCursoVida(@Param("desde") LocalDate desde,
                                                  @Param("hasta") LocalDate hasta);

    @Query("SELECT c.paciente.genero AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE c.fechaNotificacion BETWEEN :desde AND :hasta " +
            "GROUP BY c.paciente.genero")
    List<ConteoCategoria> contarCasosPorSexo(@Param("desde") LocalDate desde,
                                             @Param("hasta") LocalDate hasta);

    @Query("SELECT c.fechaNotificacion AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE c.fechaNotificacion BETWEEN :desde AND :hasta " +
            "GROUP BY c.fechaNotificacion ORDER BY c.fechaNotificacion")
    List<ConteoCategoria> contarCasosPorFecha(@Param("desde") LocalDate desde,
                                              @Param("hasta") LocalDate hasta);

    @Query("SELECT FUNCTION('DATE_FORMAT', c.fechaNotificacion, '%Y-%m') AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE FUNCTION('DATE_FORMAT', c.fechaNotificacion, '%Y-%m') BETWEEN :desde AND :hasta " +
            "GROUP BY FUNCTION('DATE_FORMAT', c.fechaNotificacion, '%Y-%m') ORDER BY categoria")
    List<ConteoCategoria> contarCasosPorMes(@Param("desde") String desde, @Param("hasta") String hasta);


    @Query("SELECT c.semanaEpidemiologica AS categoria, COUNT(c) AS total " +
            "FROM Caso c WHERE c.semanaEpidemiologica BETWEEN :desde AND :hasta " +
            "GROUP BY c.semanaEpidemiologica ORDER BY c.semanaEpidemiologica")
    List<ConteoCategoria> contarCasosPorSemana(@Param("desde") Integer desde, @Param("hasta") Integer hasta);

}
