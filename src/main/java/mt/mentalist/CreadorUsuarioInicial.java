package mt.mentalist;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mt.mentalist.modelo.Entidades.Usuario;
import mt.mentalist.modelo.Enum.Rol;
import mt.mentalist.repositorio.UsuarioRepositorio;
import mt.mentalist.servicio.Seguridad.EncriptacionServicio;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreadorUsuarioInicial {

    private final UsuarioRepositorio usuarioRepositorio;
    private final EncriptacionServicio encriptacionServicio;

    @PostConstruct
    public void crearUsuarios() {
        crearUsuarioSiNoExiste("admin46", "Admin1234*", "Administrador General", "admin1@mental.local", "3100000000", Rol.ADMIN);
        crearUsuarioSiNoExiste("medico46", "Medico1234*", "Dr. Juan Pérez", "medico1@mental.local", "3200000000", Rol.MEDICO);
    }

    private void crearUsuarioSiNoExiste(String username, String rawPassword, String nombre, String correo, String telefono, Rol rol) {
        if (usuarioRepositorio.findByUsuario(username).isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setUsuario(username); // sin encriptar
            usuario.setContrasena(encriptacionServicio.encriptarContraseña(rawPassword));
            usuario.setNombre(encriptacionServicio.encriptarTexto(nombre));
            usuario.setCorreo(encriptacionServicio.encriptarTexto(correo));
            usuario.setTelefono(encriptacionServicio.encriptarTexto(telefono));
            usuario.setRol(rol);

            usuarioRepositorio.save(usuario);
            System.out.println("✔ Usuario " + username + " creado exitosamente.");
        } else {
            System.out.println("ℹ Usuario " + username + " ya existe.");
        }
    }
}