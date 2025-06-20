package mt.mentalist.DTO.Funciones.Grafico;

import lombok.AllArgsConstructor;
import lombok.Data;
import mt.mentalist.repositorio.ConteoCategoria;
import java.util.List;
import java.awt.Image;

@Data
@AllArgsConstructor
public class SeccionGraficoDTO {
    private String titulo;
    private Image graficoImagen;
    private List<ConteoCategoria> datos; // esta se usará en un subreporte
}
