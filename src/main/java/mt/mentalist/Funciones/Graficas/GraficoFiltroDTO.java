package mt.mentalist.Funciones.Graficas;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GraficoFiltroDTO {

    private LocalDate desde;
    private LocalDate hasta;

    private String mesDesde;
    private String mesHasta;

    private Integer semanaDesde;
    private Integer semanaHasta;


    private boolean isPastel;
}

