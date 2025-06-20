package mt.mentalist.DTO.Basicas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import mt.mentalist.modelo.Enum.TipoDiagnostico;


import java.time.LocalDate;

@Data
public class DiagnosticoEspecificoDTO {

    private  Integer idDiagnosticoEspecifico;

    @NotNull(message = "El tipo de diagnóstico es obligatorio")
    private TipoDiagnostico tipoDiagnostico;

    @NotBlank(message = "El código CIE es obligatorio")
    @Size(max = 10, message = "El código CIE no debe superar los 10 caracteres")
    private String codigoCie;

    @Size(max = 500, message = "Las observaciones médicas no deben superar los 500 caracteres")
    private String observacionesMedicas;

    @NotNull(message = "La fecha del diagnóstico es obligatoria")
    @PastOrPresent(message = "La fecha del diagnóstico no puede ser futura")
    private LocalDate fechaDiagnostico;
}
