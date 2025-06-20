package mt.mentalist.repositorio;
/**
 * Proyección para consultas agregadas que retornan una categoría (e.g., diagnóstico, curso de vida)
 * y el número total de casos asociados.
 */

public interface ConteoCategoria {
    String getCategoria();
    Long getTotal();
}
