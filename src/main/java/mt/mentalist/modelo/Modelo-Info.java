package mt.mentalist.modelo;
/**
 * Modelo de dominio del sistema Mentalist.
 *
 * Este paquete representa la estructura central de los datos clínicos y administrativos que gestiona el sistema.
 * Define las entidades que serán persistidas en la base de datos y los valores enumerados usados para controlar
 * opciones en formularios, filtros y validaciones.
 *
 * Subpaquetes:
 * - Entidades/:
 *     Contiene las clases anotadas con @Entity que representan tablas del sistema, como Paciente, Caso, Usuario,
 *     Diagnóstico específico, Historia Clínica, entre otras. Estas clases conforman el núcleo de la base de datos.
 *
 * - Enum/:
 *     Define tipos de datos predefinidos y controlados que estandarizan valores como género, rol, etapa,
 *     tipo de diagnóstico, tipo de documento, etc; utilizados en formularios y validaciones.
 */