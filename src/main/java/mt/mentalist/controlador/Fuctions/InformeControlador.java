package mt.mentalist.controlador.Fuctions;

import mt.mentalist.DTO.Fuctions.InformeCasoDTO;
import mt.mentalist.DTO.Fuctions.InformeHistoriaClinicaDTO;
import mt.mentalist.servicio.Funciones.InformesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mentalist-web")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://localhost",
})
public class InformeControlador {

    @Autowired
    private InformesServicio informeCasoServicio;

     @GetMapping("/InformeCaso")
    public List<InformeCasoDTO>obtenerInformeCasos(){
         return informeCasoServicio.obtenerDatosInformeCaso();
     }

     @GetMapping("/InformeHistoriaClinica")
    public List<InformeHistoriaClinicaDTO>obtenerhistoriaclinica(){
         return informeCasoServicio.obtenerDatosInformeHistoriaClinica();
     }
}
