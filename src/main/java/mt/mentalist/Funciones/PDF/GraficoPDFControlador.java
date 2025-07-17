package mt.mentalist.Funciones.PDF;

import mt.mentalist.Funciones.Graficas.GraficoFiltroDTO;
import mt.mentalist.Funciones.Graficas.ExportadorPDFServicioGraficos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentalist-web/graficos/pdf")
@PreAuthorize("hasRole('ADMIN')")
public class GraficoPDFControlador {
    @Autowired
    private ExportadorPDFServicioGraficos exportadorPDFServicioGraficos;

    private ResponseEntity<byte[]> exportar(String nombreArchivo, byte[] contenido) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(contenido);
    }

    @PostMapping("/curso-vida")
    public ResponseEntity<byte[]> descargarCursoVida(@RequestBody GraficoFiltroDTO filtro) throws Exception {
        return exportar("CursoVida.pdf", exportadorPDFServicioGraficos.generarPDFGraficoPorCursoVida(filtro));
    }

    @PostMapping("/sexo")
    public ResponseEntity<byte[]> descargarSexo(@RequestBody GraficoFiltroDTO filtro) throws Exception {
        return exportar("Sexo.pdf", exportadorPDFServicioGraficos.generarPDFGraficoPorSexo(filtro));
    }

    @PostMapping("/fecha")
    public ResponseEntity<byte[]> descargarFecha(@RequestBody GraficoFiltroDTO filtro) throws Exception {
        return exportar("Fecha.pdf", exportadorPDFServicioGraficos.generarPDFGraficoPorFecha(filtro));
    }

    @PostMapping("/mes")
    public ResponseEntity<byte[]> descargarMes(@RequestBody GraficoFiltroDTO filtro) throws Exception {
        return exportar("Mes.pdf", exportadorPDFServicioGraficos.generarPDFGraficoPorMes(filtro));
    }

    @PostMapping("/semana")
    public ResponseEntity<byte[]> descargarSemana(@RequestBody GraficoFiltroDTO filtro) throws Exception {
        return exportar("Semana.pdf", exportadorPDFServicioGraficos.generarPDFGraficoPorSemana(filtro));
    }
}
