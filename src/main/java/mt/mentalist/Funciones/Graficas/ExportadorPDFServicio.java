package mt.mentalist.Funciones.Graficas;

import mt.mentalist.Funciones.PDF.InformeCasoDTO;
import mt.mentalist.Funciones.PDF.InformeHistoriaClinicaDTO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportadorPDFServicio {

    @Autowired
    private InformesServicio informesServicio;

    public byte[] generarPDFInformeCaso(int idCaso) throws Exception{
        List<InformeCasoDTO> datos = informesServicio.obtenerDatosInformeCaso()
                .stream()
                .filter(dto -> dto.getIdCaso() == idCaso)
                .toList();
        if (datos.isEmpty()){
            throw new RuntimeException("No se encontro el caso con ID" + idCaso);
        }
        InputStream jasperStream = new ClassPathResource("reportes/informeCaso.jasper").getInputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Titulo","Informe del Caso de Salud Mental");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource);
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        return baos.toByteArray();
    }

    public byte[] generarPDFInformeHistoriaClinica(int idHistorialClinica) throws Exception {
        List<InformeHistoriaClinicaDTO> datos = informesServicio.obtenerDatosInformeHistoriaClinica()
                .stream()
                .filter(dto -> dto.getIdCaso() == idHistorialClinica)
                .toList();

        if (datos.isEmpty()) {
            throw new RuntimeException("No se encontró la historia clínica para el caso con ID: " + idHistorialClinica);
        }

        InputStream jasperStream = new ClassPathResource("reportes/informeHistoria.jasper").getInputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TITULO", "Informe de Historia Clínica");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        return baos.toByteArray();
    }

}
