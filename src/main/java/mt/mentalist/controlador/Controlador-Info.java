package mt.mentalist.controlador;

/**
 * Contiene todos los controladores REST que exponen los endpoints públicos del sistema.
 *
 * Subpaquetes:
 * - Basicos/:
 *     Incluye controladores que permiten realizar operaciones CRUD sobre entidades como Usuario, Paciente, Caso, etc.
 *     Por ejemplo: `PacienteControlador` expone endpoints para crear, consultar o eliminar pacientes.
 *
 * - Funciones/:
 *     Controladores dedicados a funcionalidades transversales, como la generación de informes en PDF
 *     o la consulta a la base de datos CIE11 para diagnósticos.
 *
 * - Seguridad/:
 *     Controlador encargado del proceso de login y autenticación de usuarios (`AuthControlador`).
 *     A través de este, se obtienen y validan tokens JWT y se maneja la sesión del usuario autenticado.
 */