package mt.mentalist.servicio.Funciones.Grafica;

import lombok.RequiredArgsConstructor;
import mt.mentalist.DTO.Funciones.Grafico.GraficoFiltroDTO;
import mt.mentalist.repositorio.CasoRepositorio;
import mt.mentalist.repositorio.ConteoCategoria;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraficoServicio {

    private final CasoRepositorio casoRepositorio;

    public byte[] generarGraficoTipoDiagnostico(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorTipoDiagnostico(filtro.getDesde(), filtro.getHasta());
        return generarGrafico("Casos por Tipo de Diagnóstico", "Tipo", "Cantidad", datos, filtro.isPastel());
    }

    public byte[] generarGraficoCursoVida(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorCursoVida(filtro.getDesde(), filtro.getHasta());
        return generarGrafico("Casos por Curso de Vida", "Curso", "Cantidad", datos, filtro.isPastel());
    }

    public byte[] generarGraficoSexo(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorSexo(filtro.getDesde(), filtro.getHasta());
        return generarGrafico("Casos por Sexo del Paciente", "Sexo", "Cantidad", datos, filtro.isPastel());
    }

    public byte[] generarGraficoPorFecha(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorFecha(filtro.getDesde(), filtro.getHasta());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ConteoCategoria dato : datos) {
            dataset.addValue(dato.getTotal(), "Casos", dato.getCategoria().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Casos por Fecha de Notificación",
                "Fecha",
                "Cantidad",
                dataset
        );
        return chartToPNG(chart, 400, 300);
    }

    public byte[] generarGraficoPorMes(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorMes(filtro.getMesDesde(), filtro.getMesHasta());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ConteoCategoria dato : datos) {
            dataset.addValue(dato.getTotal(), "Casos", dato.getCategoria().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Casos por Mes",
                "Mes",
                "Cantidad",
                dataset
        );
        return chartToPNG(chart, 400, 300);
    }

    public byte[] generarGraficoPorSemana(GraficoFiltroDTO filtro) {
        List<ConteoCategoria> datos = casoRepositorio.contarCasosPorSemana(filtro.getSemanaDesde(), filtro.getSemanaHasta());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ConteoCategoria dato : datos) {
            dataset.addValue(dato.getTotal(), "Casos", dato.getCategoria().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Casos por Semana Epidemiológica",
                "Semana",
                "Cantidad",
                dataset
        );
        return chartToPNG(chart, 400, 300);
    }

    // Auxiliar general que decide entre pastel y barras
    private byte[] generarGrafico(String titulo, String ejeX, String ejeY, List<ConteoCategoria> datos, boolean pastel) {
        return pastel
                ? generarGraficoPastel(titulo, datos)
                : generarGraficoBarras(titulo, ejeX, ejeY, datos);
    }

    // Barras
    private byte[] generarGraficoBarras(String titulo, String ejeX, String ejeY, List<ConteoCategoria> datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ConteoCategoria dato : datos) {
            dataset.addValue(dato.getTotal(), "Casos", dato.getCategoria().toString());
        }
        JFreeChart chart = ChartFactory.createBarChart(titulo, ejeX, ejeY, dataset);
        return chartToPNG(chart, 400, 300);
    }

    // Pastel
    private byte[] generarGraficoPastel(String titulo, List<ConteoCategoria> datos) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (ConteoCategoria dato : datos) {
            dataset.setValue(dato.getCategoria().toString(), dato.getTotal());
        }
        JFreeChart chart = ChartFactory.createPieChart(titulo, dataset, true, true, false);
        chart.getTitle().setPaint(Color.DARK_GRAY);
        return chartToPNG(chart, 400, 300);
    }

    // Convertir gráfico a PNG
    private byte[] chartToPNG(JFreeChart chart, int width, int height) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsPNG(baos, chart, width, height);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar grafico", e);
        }
    }
}
