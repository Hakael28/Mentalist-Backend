package mt.mentalist.DTO;

/**
 * Los DTO (Data Transfer Objects) en el sistema Mentalist permiten intercambiar datos entre el cliente (frontend)
 * y el servidor (backend) de manera controlada, evitando exponer directamente las entidades del sistema.
 *
 * Estos objetos actúan como estructuras intermedias para validar, estructurar y simplificar la información
 * relacionada con usuarios, pacientes, casos de salud mental, diagnósticos, y generación de reportes.
 *
 * Subpaquetes:
 * - DTOBasics/:
 *     Agrupa los DTOs de entrada y salida para las entidades principales.
 *     Por ejemplo: `PacienteDTO` representa los datos generales del paciente utilizados en la interfaz.
 *
 * - Funciones/:
 *     Incluye DTOs especializados para procesos funcionales del sistema, como la autenticación de usuarios
 *     (`LoginRequestDTO`, `LoginResponseDTO`) y la generación de informes clínicos en PDF (`InformeCasoDTO`, `InformeHistoriaClinicaDTO`).
 */