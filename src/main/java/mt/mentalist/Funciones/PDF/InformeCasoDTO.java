package mt.mentalist.Funciones.PDF;

import lombok.Data;
import mt.mentalist.CursoVida.Etapa;
import mt.mentalist.Paciente.Genero;
import mt.mentalist.DiagnosticoEspecifico.TipoDiagnostico;
import mt.mentalist.Paciente.TipoDocumento;

import java.time.LocalDate;

@Data
public class InformeCasoDTO {
    //Datos del paciente
    private Integer idPaciente;
    private TipoDocumento tipoDocumento;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private int edad;
    private Genero genero;
    private String nacionalidad;
    private String telefono;
    private String correo;
    private String direccion;

    //Datos Basicos del Caso
    private Integer idCaso;
    private LocalDate fechaNotificacion;
    private int semanaEpidemiologica;
    private LocalDate fechaIngreso;
    private LocalDate fechaRevisionHistoria;
    private String remisionRutaSalud;

    //Datos del Diagnostico
    private TipoDiagnostico tipodiagnostico;
    private String codigoCie;
    private String observacionesMedicas;
    private LocalDate Fechadiagnostico;

    //Datos complementarios del informe
    private String nombreAreaOcurrencia;
    private String descripcionRutaAtencion;
    private String nombreEabp;
    private Etapa etapaCursoVida;
}
