package mt.mentalist.Funciones.PDF;

import lombok.Data;
import mt.mentalist.Paciente.TipoDocumento;

@Data
public class InformeHistoriaClinicaDTO {
    private Integer idHistoriaClinica;
    private String descripcionHistoria;

    // dartos del paciente
    private Integer idPaciente;
    private TipoDocumento tipoDocumento;
    private String nombreCompleto;
    private int edad;

    private Integer idCaso;


}

