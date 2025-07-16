package mt.mentalist.Funciones.Seguridad;

/**
 * Capa de servicios del sistema Mentalist.
 *
 * Este paquete contiene la lógica de negocio que gestiona los procesos clínicos y administrativos
 * relacionados con los casos de salud mental. Los servicios actúan como puente entre los controladores
 * (API REST) y los repositorios (base de datos), aplicando reglas, validaciones y transformaciones necesarias.
 *
 * Subpaquetes:
 * - Basicos/:
 *     Servicios orientados a las entidades clínicas y administrativas del sistema, como Paciente, Caso,
 *     Historia Clínica, Ruta de Atención, entre otros. Contienen interfaces (`I...Servicio`) y sus implementaciones.
 *
 * - Funciones/:
 *     Servicios especializados que cumplen funciones transversales o complementarias como:
 *     - Consultas al API CIE11 (subpaquete API).
 *     - Generación de informes en formato PDF (subpaquete InformesPDF).
 *
 * - Seguridad/:
 *     Lógica de autenticación y encriptación de credenciales. Aquí se encuentra la gestión del inicio de sesión,
 *     la generación y validación de tokens JWT y utilidades criptográficas.
 */