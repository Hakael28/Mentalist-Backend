package mt.mentalist.servicio.Funciones.InformesPDF;

import mt.mentalist.DTO.Funciones.Informes.InformeCasoDTO;
import mt.mentalist.DTO.Funciones.Informes.InformeHistoriaClinicaDTO;
import mt.mentalist.servicio.Seguridad.Seguridad.EncriptacionServicio;
import mt.mentalist.modelo.Enum.Etapa;
import mt.mentalist.modelo.Enum.Genero;
import mt.mentalist.modelo.Enum.TipoDiagnostico;
import mt.mentalist.modelo.Enum.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InformesServicio {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EncriptacionServicio encriptacionServicio;

    public List<InformeCasoDTO>obtenerDatosInformeCaso(){
        String sql = """
            SELECT
                p.id_paciente,
                p.nombre_completo,
                p.tipo_documento,
                p.telefono,
                p.direccion,
                p.fecha_nacimiento,
                p.edad,
                p.genero,
                p.nacionalidad,
                p.correo,
                c.id_caso,
                c.fecha_notificacion,
                c.semana_epidemiologica,
                c.fecha_ingreso,
                c.fecha_revision_historia,
                c.remision_ruta_salud,
                d.tipo_diagnostico,
                d.codigo_cie,
                d.fecha_diagnostico,
                d.observaciones_medicas,
                a.nombre AS area_ocurrencia,
                r.descripcion AS ruta_atencion,
                e.nombre AS nombre_eapb,
                cv.etapa AS curso_vida
            FROM caso c
            JOIN paciente p ON c.id_paciente = p.id_paciente
            LEFT JOIN diagnostico_especifico d ON c.id_diagnostico_especifico = d.id_diagnostico_especifico
            LEFT JOIN area_ocurrencia a ON c.id_area_ocurrencia = a.id_area_ocurrencia
            LEFT JOIN ruta_atencion r ON c.id_ruta_atencion = r.id_ruta_atencion
            LEFT JOIN eapb e ON c.id_eapb = e.id_eapb
            LEFT JOIN curso_vida cv ON c.id_curso_vida = cv.id_curso_vida
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            InformeCasoDTO dto = new InformeCasoDTO();

            int idPaciente = rs.getInt("id_paciente");
            dto.setIdPaciente(rs.wasNull() ? null : idPaciente);

            dto.setNombreCompleto(encriptacionServicio.desencriptarTexto(rs.getString("nombre_completo")));

            String tipoDoc = rs.getString("tipo_documento");
            dto.setTipoDocumento(tipoDoc != null ? TipoDocumento.valueOf(tipoDoc) : null);

            dto.setTelefono(encriptacionServicio.desencriptarTexto(rs.getString("telefono")));
            dto.setDireccion(encriptacionServicio.desencriptarTexto(rs.getString("direccion")));

            Date fechaNac = rs.getDate("fecha_nacimiento");
            dto.setFechaNacimiento(fechaNac != null ? ((java.sql.Date) fechaNac).toLocalDate() : null);

            int edad = rs.getInt("edad");
            dto.setEdad(rs.wasNull() ? null : edad);

            String genero = rs.getString("genero");
            dto.setGenero(genero != null ? Genero.valueOf(genero) : null);

            dto.setNacionalidad(encriptacionServicio.desencriptarTexto(rs.getString("nacionalidad")));
            dto.setCorreo(encriptacionServicio.desencriptarTexto(rs.getString("correo")));

            int idCaso = rs.getInt("id_caso");
            dto.setIdCaso(rs.wasNull() ? null : idCaso);

            Date fechaNot = rs.getDate("fecha_notificacion");
            dto.setFechaNotificacion(fechaNot != null ? ((java.sql.Date) fechaNot).toLocalDate() : null);

            int semanaEpi = rs.getInt("semana_epidemiologica");
            dto.setSemanaEpidemiologica(rs.wasNull() ? null : semanaEpi);

            Date fechaIng = rs.getDate("fecha_ingreso");
            dto.setFechaIngreso(fechaIng != null ? ((java.sql.Date) fechaIng).toLocalDate() : null);

            Date fechaRev = rs.getDate("fecha_revision_historia");
            dto.setFechaRevisionHistoria(fechaRev != null ? ((java.sql.Date) fechaRev).toLocalDate() : null);

            dto.setRemisionRutaSalud(encriptacionServicio.desencriptarTexto(rs.getString("remision_ruta_salud")));

            String tipoDiag = rs.getString("tipo_diagnostico");
            dto.setTipodiagnostico(tipoDiag != null ? TipoDiagnostico.valueOf(tipoDiag) : null);

            dto.setCodigoCie(encriptacionServicio.desencriptarTexto(rs.getString("codigo_cie")));

            Date fechaDiag = rs.getDate("fecha_diagnostico");
            dto.setFechadiagnostico(fechaDiag != null ? ((java.sql.Date) fechaDiag).toLocalDate() : null);

            dto.setObservacionesMedicas(rs.getString("observaciones_medicas"));
            dto.setNombreAreaOcurrencia(rs.getString("area_ocurrencia"));
            dto.setDescripcionRutaAtencion(rs.getString("ruta_atencion"));
            dto.setNombreEabp(rs.getString("nombre_eapb"));

            String etapa = rs.getString("curso_vida");
            dto.setEtapaCursoVida(etapa != null ? Etapa.valueOf(etapa) : null);

            return dto;
        });
    }

    public List <InformeHistoriaClinicaDTO> obtenerDatosInformeHistoriaClinica() {
        String sql = """
                SELECT
                  hc.id_historia_clinica,
                  hc.descripcion_historia,
                  hc.id_caso_H, 
                  p.id_paciente,
                  p.tipo_documento,
                  p.nombre_completo,
                  p.edad
                FROM historia_clinica hc
                JOIN paciente p ON hc.id_paciente_H = p.id_paciente
              """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            InformeHistoriaClinicaDTO dto = new InformeHistoriaClinicaDTO();

            int idHistoria = rs.getInt("id_historia_clinica");
            dto.setIdHistoriaClinica(rs.wasNull() ? null : idHistoria);

            dto.setDescripcionHistoria(encriptacionServicio.desencriptarTexto(rs.getString("descripcion_historia")));

            int idPaciente = rs.getInt("id_paciente");
            dto.setIdPaciente(rs.wasNull() ? null : idPaciente);

            String tipoDoc = rs.getString("tipo_documento");
            dto.setTipoDocumento(tipoDoc != null ? TipoDocumento.valueOf(tipoDoc) : null);

            dto.setNombreCompleto(encriptacionServicio.desencriptarTexto(rs.getString("nombre_completo")));

            int edad = rs.getInt("edad");
            dto.setEdad(rs.wasNull() ? null : edad);

            int idCaso = rs.getInt("id_caso_H");
            dto.setIdCaso(rs.wasNull()?null:idCaso);

            return dto;
        });

    }
}
