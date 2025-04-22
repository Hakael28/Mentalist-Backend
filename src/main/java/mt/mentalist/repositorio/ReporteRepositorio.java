/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mt.mentalist.repositorio;
import  org.springframework.data.jpa.repository.JpaRepository;
import mt.mentalist.modelo.Reporte;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReporteRepositorio extends JpaRepository<Reporte, Integer> {
     Optional<Reporte> findTopByOrderByIdReporteDesc();

}
