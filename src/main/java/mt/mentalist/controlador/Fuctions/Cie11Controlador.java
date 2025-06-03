package mt.mentalist.controlador.Fuctions;

import mt.mentalist.servicio.Funciones.API.Cie11Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cie11")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://localhost",
})
public class Cie11Controlador {

    @Autowired
    private Cie11Servicio cie11Servicio;

    @GetMapping("/sugerencias")
    public ResponseEntity<List<Map<String,String>>>obtenerSugerencias(@RequestParam("q") String texto){
        List<Map<String, String>> resultados = cie11Servicio.buscarDiagnosticos(texto);
        return ResponseEntity.ok(resultados);
    }
}
