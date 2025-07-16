package mt.mentalist.Funciones.Graficas;

import mt.mentalist.Caso.CasoRepositorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ExportadorPDFServicioGraficos {
    @Autowired
    private CasoRepositorio casoRepositorio;
    @Autowired
    private GraficoServicio graficoServicio;

    public byte[] generarPDFGraficoPorCursoVida(GraficoFiltroDTO filtro) throws Exception {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorCursoVida(filtro.getDesde(), filtro.getHasta());
        return generarInformeConGrafico("reportes/informeGraficoGeneral.jasper", "Casos por Curso de Vida", datos,
                graficoServicio.generarGraficoCursoVida(filtro));
    }

    public byte[] generarPDFGraficoPorSexo(GraficoFiltroDTO filtro) throws Exception {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorSexo(filtro.getDesde(), filtro.getHasta());
        return generarInformeConGrafico("reportes/informeGraficoGeneral.jasper", "Casos por Sexo del Paciente", datos,
                graficoServicio.generarGraficoSexo(filtro));
    }

    public byte[] generarPDFGraficoPorFecha(GraficoFiltroDTO filtro) throws Exception {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorFecha(filtro.getDesde(), filtro.getHasta());
        return generarInformeConGrafico("reportes/informeGraficoGeneral.jasper", "Casos por Fecha de Notificación", datos,
                graficoServicio.generarGraficoPorFecha(filtro));
    }

    public byte[] generarPDFGraficoPorMes(GraficoFiltroDTO filtro) throws Exception {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorMes(filtro.getMesDesde(), filtro.getMesHasta());
        return generarInformeConGrafico("reportes/informeGraficoGeneral.jasper", "Casos por Mes", datos,
                graficoServicio.generarGraficoPorMes(filtro));
    }

    public byte[] generarPDFGraficoPorSemana(GraficoFiltroDTO filtro) throws Exception {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorSemana(filtro.getSemanaDesde(), filtro.getSemanaHasta());
        return generarInformeConGrafico("reportes/informeGraficoGeneral.jasper", "Casos por Semana Epidemiológica", datos,
                graficoServicio.generarGraficoPorSemana(filtro));
    }

    private byte[] generarInformeConGrafico(String rutaJasper, String titulo, List<ConteoCategoria> datos, byte[] imagenPng) throws Exception {
        BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenPng));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("graficoImagen", imagen);
        parametros.put("titulo", titulo);

        InputStream plantilla = new ClassPathResource(rutaJasper).getInputStream();
        JasperPrint print = JasperFillManager.fillReport(plantilla, parametros, new JRBeanCollectionDataSource(datos));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(print, baos);
        return baos.toByteArray();
    }

    public byte[] generarPDFGraficoCombinado(GraficoFiltroDTO filtro) throws Exception {
        // Obtener los datos e imágenes
        List<ConteoCategoria> datosDiagnostico = casoRepositorio.contarCasosPorTipoDiagnostico(filtro.getDesde(), filtro.getHasta());
        List<ConteoCategoria> datosCurso = casoRepositorio.contarCasosPorCursoVida(filtro.getDesde(), filtro.getHasta());
        List<ConteoCategoria> datosSexo = casoRepositorio.contarCasosPorSexo(filtro.getDesde(), filtro.getHasta());
        List<ConteoCategoria> datosFecha = casoRepositorio.contarCasosPorFecha(filtro.getDesde(), filtro.getHasta());

        BufferedImage graficoDiagnostico = ImageIO.read(new ByteArrayInputStream(graficoServicio.generarGraficoTipoDiagnostico(filtro)));
        BufferedImage graficoCurso = ImageIO.read(new ByteArrayInputStream(graficoServicio.generarGraficoCursoVida(filtro)));
        BufferedImage graficoSexo = ImageIO.read(new ByteArrayInputStream(graficoServicio.generarGraficoSexo(filtro)));
        BufferedImage graficoFecha = ImageIO.read(new ByteArrayInputStream(graficoServicio.generarGraficoPorFecha(filtro)));

        // Crear lista de secciones
        List<SeccionGraficoDTO> secciones = List.of(
                new SeccionGraficoDTO("Casos por Tipo de Diagnóstico", graficoDiagnostico, datosDiagnostico),
                new SeccionGraficoDTO("Casos por Curso de Vida", graficoCurso, datosCurso),
                new SeccionGraficoDTO("Casos por Sexo del Paciente", graficoSexo, datosSexo),
                new SeccionGraficoDTO("Casos por Fecha de Notificación", graficoFecha, datosFecha)
        );

        // Generar el informe .jasper multipágina
        InputStream plantilla = new ClassPathResource("reportes/informeGraficoCombinado.jasper").getInputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(secciones);

        Map<String, Object> parametros = new HashMap<>(); // vacíos por ahora
        JasperPrint print = JasperFillManager.fillReport(plantilla, parametros, dataSource);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(print, baos);
        return baos.toByteArray();
    }

}


