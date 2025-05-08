package mt.mentalist.servicio.Basicos;

import java.util.List;
import java.util.Optional;

import mt.mentalist.DTO.DTOBasics.HistoriaClinicaDTO;
import mt.mentalist.modelo.HistoriaClinica;

public interface IHistoriaClinicaServicio {

    // Metodo para listar la historia clinica
    List<HistoriaClinicaDTO> listarClinica();

    List<HistoriaClinica> obtenerHistoriasClinicasPorPaciente(Integer idPaciente);

    // Metodo para seleccionar el ultimo  registro de HistoriaClinica
    Optional<HistoriaClinica> findTopByOrderByIdHistorialClinicaDesc();

    //Metodo para buscar la historia clinica
    HistoriaClinicaDTO buscarClinicaId(Integer idHistoriaClinica);

    //Metodo para guardar la historia clinica
    HistoriaClinicaDTO guardarClinica(HistoriaClinicaDTO dto);

    //Metodo para eliminar la historia clinica
    void eliminarClinica(Integer idHistoriaClinica);

}
