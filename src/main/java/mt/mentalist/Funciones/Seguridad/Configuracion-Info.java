package mt.mentalist.Funciones.Seguridad;

/**
 * Este paquete define las configuraciones globales necesarias para el funcionamiento del sistema,
 * tanto en el consumo de servicios externos como en los aspectos de seguridad y encriptación.
 *
 * Subpaquetes:
 * - API/:
 *     Contiene configuraciones específicas para interactuar con servicios externos como el CIE11.
 *     Por ejemplo, `RestTemplateConfig` permite hacer peticiones HTTP REST a otras APIs.
 *
 * - Seguridad/:
 *     Asegura la aplicación utilizando Spring Security y JWT (JSON Web Token).
 *     Contiene filtros de autenticación (`JwtAuthenticationFilter`), herramientas para generar y validar tokens (`JwtUtil`)
 *     y la clase `ConfiguracionSeguridad` que define las reglas de acceso y autorización a los endpoints.
 */