package mt.mentalist;

/**
 * Proyecto Mentalist — Sistema para el registro, análisis y visualización de casos de salud mental.
 *
 * Organización por capas y responsabilidades:
 *
 * - configuracion/            → Configuración general del sistema: seguridad, API externa (CIE11), y beans.
 * - controlador/              → Controladores REST agrupados por funcionalidad: básicos, funciones, seguridad.
 * - DTO/                      → Objetos de transferencia de datos para entrada y salida (agrupados por dominio).
 * - exception/                → Excepciones personalizadas del sistema.
 * - main/                     → Clases principales de ejecución: Spring Boot (MentalistApplication) y Swing.
 * - modelo/                   → Entidades JPA y enumeraciones usadas por el sistema.
 * - repositorio/              → Interfaces Spring Data JPA para acceso a la base de datos.
 * - servicio/                 → Lógica de negocio agrupada en servicios básicos y funcionales.
 */