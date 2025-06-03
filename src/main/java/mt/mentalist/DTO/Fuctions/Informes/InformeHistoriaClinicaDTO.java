package mt.mentalist.DTO.Fuctions.Informes;

import lombok.Data;
import mt.mentalist.modelo.Enum.TipoDocumento;

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

