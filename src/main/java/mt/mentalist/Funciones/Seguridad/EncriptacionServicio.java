package mt.mentalist.Funciones.Seguridad;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncriptacionServicio {

    private final BCryptPasswordEncoder encoder;
    private final BasicTextEncryptor textEncryptor;

    public EncriptacionServicio(BCryptPasswordEncoder encoder,
                                @Value("${JASYPT_ENCRYPTION_PASSWORD}") String claveJasypt) {
        this.encoder = encoder;

        if (claveJasypt == null || claveJasypt.isBlank()) {
            throw new IllegalStateException("Falta la variable de entorno JASYPT_ENCRYPTION_PASSWORD");
        }
        this.textEncryptor = new BasicTextEncryptor();
        this.textEncryptor.setPassword(claveJasypt);
        System.out.println("Clave Jasypt cargada correctamente.");
    }

    // BCrypt para contraseñas
    public String encriptarContraseña(String textoPlano) {
        return encoder.encode(textoPlano);
    }

    public boolean verificarContraseña(String textoPlano, String hashGuardado) {
        return encoder.matches(textoPlano, hashGuardado);
    }

    // Jasypt para datos sensibles reversibles (no contraseñas)
    public String encriptarTexto(String textoPlano) {
        return textEncryptor.encrypt(textoPlano);
    }

    public String desencriptarTexto(String textoEncriptado) {
        return textEncryptor.decrypt(textoEncriptado);
    }
}

