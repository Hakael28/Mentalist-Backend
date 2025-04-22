package mt.mentalist.controlador;

import mt.mentalist.servicio.PacienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//http://localhost:8084/mentalist-web
@RequestMapping("mentalist-web")
@CrossOrigin(value = "http://localhost:4200")
public class PacienteControlador {
    private static final Logger logger= LoggerFactory.getLogger(PacienteControlador.class);

    @Autowired
    private PacienteServicio pacienteServicio;


}
