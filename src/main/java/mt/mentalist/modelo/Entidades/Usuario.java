package mt.mentalist.modelo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import mt.mentalist.modelo.Enum.Rol;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String nombre;

    @Column(length = 255, nullable = false, unique = true)
    @NotNull
    @Size(max = 255)
    private String usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(length = 255, nullable = false)
    @NotNull
    @Size(min = 8, max = 255)
    @ToString.Exclude
    private String contrasena;

    @Column(length = 255)
    @Size(max = 255)
    private String correo;
    
    @Column(length = 255)
    @Size(max = 255)
    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Caso> casos=new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Reporte> reportes=new ArrayList<>();

}
