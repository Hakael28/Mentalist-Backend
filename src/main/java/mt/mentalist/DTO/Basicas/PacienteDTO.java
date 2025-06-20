package mt.mentalist.DTO.Basicas;

import jakarta.validation.constraints.*;
import lombok.Data;
import mt.mentalist.modelo.Enum.Genero;
import mt.mentalist.modelo.Enum.TipoDocumento;

import java.time.LocalDate;
import java.time.Period;

@Data
public class PacienteDTO {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Integer idPaciente;

    @NotNull(message = "El tipo de documento es obligatorio")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El nombre completo no puede estar vacío")
    @Size(max = 255, message = "El nombre completo no debe superar los 255 caracteres")
    private String nombreCompleto;
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    private int edad;

    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @Size(max = 255, message = "La nacionalidad no debe superar los 255 caracteres")
    private String nacionalidad;
    private String telefono;

    @Email(message = "El correo no es válido")
    @Size(max = 255, message = "El correo no debe superar los 255 caracteres")
    private String correo;

    @Size(max = 255, message = "La dirección no debe superar los 255 caracteres")
    private String direccion;

    //  Getter personalizado: calcula edad en tiempo real
    public int getEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}
