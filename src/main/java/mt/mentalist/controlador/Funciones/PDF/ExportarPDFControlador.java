package mt.mentalist.controlador.Funciones.PDF;

import mt.mentalist.servicio.Funciones.InformesPDF.ExportadorPDFServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mentalist-web/basicos")
public class ExportarPDFControlador {

    @Autowired
    private ExportadorPDFServicio exportadorPDFServicio;

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/InformeCaso/pdf/{idCaso}")
    public ResponseEntity<byte[]>descargarPDFCaso(@PathVariable int idCaso) throws Exception{
        byte[] pdfBytes = exportadorPDFServicio.generarPDFInformeCaso(idCaso);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("Informe_Caso_"+idCaso+".pdf")
                .build());
        return new ResponseEntity<>(pdfBytes,headers, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/InformeHistoriaClinica/pdf/{idHistorialClinica}")
    public ResponseEntity<byte[]>descargarPDFHistoriaClinica(@PathVariable int idHistorialClinica) throws Exception{
        byte[] pdfBytes = exportadorPDFServicio.generarPDFInformeHistoriaClinica(idHistorialClinica);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("Informe_HistoriaClinica_"+idHistorialClinica+".pdf")
                .build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
