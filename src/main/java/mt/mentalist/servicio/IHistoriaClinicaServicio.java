package mt.mentalist.servicio;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.HistoriaClinicaDTO;
import mt.mentalist.modelo.HistoriaClinica;

public interface IHistoriaClinicaServicio {

    // Metodo para listar la historia clinica
    public List<HistoriaClinica> listarClinica();

    public List<HistoriaClinica> obtenerHistoriasClinicasPorPaciente(Integer idPaciente);

    // Metodo para seleccionar el ultimo  registro de HistoriaClinica
    public Optional<HistoriaClinica> findTopByOrderByIdHistorialClinicaDesc();

    //Metodo para buscar la historia clinica
    public HistoriaClinica buscarClinicaId(Integer idHistoriaClinica);

    //Metodo para guardar la historia clinica
    public HistoriaClinica guardarClinica(HistoriaClinicaDTO dto);

    //Metodo para eliminar la historia clinica
    public void eliminarClinica(Integer idHistoriaClinica);

}
