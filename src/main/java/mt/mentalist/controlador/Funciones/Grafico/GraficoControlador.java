package mt.mentalist.controlador.Funciones.Grafico;

import lombok.RequiredArgsConstructor;
import mt.mentalist.DTO.Funciones.Grafico.GraficoFiltroDTO;
import mt.mentalist.servicio.Funciones.Grafica.GraficoServicio;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentalist-web/basicos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class GraficoControlador {

    private final GraficoServicio graficoServicio;

    private ResponseEntity<byte[]> respuestaConGraficoPng(byte[] imagen) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"grafico.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(imagen);
    }

    @PostMapping("/diagnostico")
    public ResponseEntity<byte[]> graficoDiagnostico(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoTipoDiagnostico(filtro));
    }

    @PostMapping("/curso-vida")
    public ResponseEntity<byte[]> graficoCursoVida(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoCursoVida(filtro));
    }

    @PostMapping("/sexo")
    public ResponseEntity<byte[]> graficoSexo(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoSexo(filtro));
    }

    @PostMapping("/fecha")
    public ResponseEntity<byte[]> graficoFecha(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoPorFecha(filtro));
    }

    @PostMapping("/mes")
    public ResponseEntity<byte[]> graficoMes(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoPorMes(filtro));
    }

    @PostMapping("/semana")
    public ResponseEntity<byte[]> graficoSemana(@RequestBody GraficoFiltroDTO filtro) {
        return respuestaConGraficoPng(graficoServicio.generarGraficoPorSemana(filtro));
    }
}

