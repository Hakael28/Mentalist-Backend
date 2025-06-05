package mt.mentalist.exception;

/**
 * Manejo centralizado de excepciones para responder adecuadamente cuando ocurre un error en el sistema.
 *
 * - RecursoNoEncontradoExcepcion:
 *     Se lanza cuando no se encuentra una entidad específica en la base de datos.
 *     Esto permite enviar al cliente respuestas controladas como 404 (Not Found), evitando errores genéricos o poco descriptivos.
 */