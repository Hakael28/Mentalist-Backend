/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mt.mentalist.modelo.Entidades;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import mt.mentalist.modelo.Enum.TipoReporte;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reporte")
public class Reporte implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte", nullable = false)
    private Integer idReporte;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoReporte tipoReporte;

    @Column(length = 255)
    @Size(max = 255)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
}
