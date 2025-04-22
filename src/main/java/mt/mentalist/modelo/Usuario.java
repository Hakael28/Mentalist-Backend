package mt.mentalist.modelo;

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

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(length = 100, nullable = false)
    @NotNull
    @Size(min = 8, max = 100)
    @ToString.Exclude
    private String contraseña;

    @Column(length = 100)
    @Size(max = 100)
    @Email
    private String correo;
    
    @Column(length = 15)
    @Size(max = 15)  
    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Caso> casos=new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Reporte> reportes=new ArrayList<>();

}
