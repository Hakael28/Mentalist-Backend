package mt.mentalist.Funciones.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/cie11")
public class Cie11Controlador {

    @Autowired
    private Cie11Servicio cie11Servicio;

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/sugerencias")
    public ResponseEntity<List<Map<String,String>>>obtenerSugerencias(@RequestParam("q") String texto){
        List<Map<String, String>> resultados = cie11Servicio.buscarDiagnosticos(texto);
        return ResponseEntity.ok(resultados);
    }
}
