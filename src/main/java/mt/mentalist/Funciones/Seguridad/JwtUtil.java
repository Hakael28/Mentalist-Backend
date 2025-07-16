package mt.mentalist.Funciones.Seguridad;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    //Inyecta la clave secreta desde application.properties
    @Value("${jwt.secret}")
    private String secretKey;
    //Tiempo de expiración del token (24 horas en milisegundos)
    private static final long EXPIRATION_TIME=86400000;
    //Clave ya procesada para firmar/verificar JWT
    private Key key;

    // Inicialización automática después de que Spring inyecte el valor de `secretKey`
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    //Genera un JWT con claims personalizados: usuario, rol e ID
    public String generarToken(String usuario, String rol, Integer idUsuario){
        return Jwts.builder()
                .setSubject(usuario) // Establece el usuario como 'subject' principal del token
                .claim("rol", rol) //Claim personalizado de rol
                .claim("id", idUsuario) // Claim personalizado del id del usuario
                .setIssuedAt(new Date()) //Fecha de emision del token
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) //Tiempo de expiracion de 24h
                .signWith(key, SignatureAlgorithm.HS256) //Firma el token utilizando el algoritmo HMAC-SHA256
                .compact();
    }

    //Extrae el subject (nombre de usuario) del token JWT
    public String obtenerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Extrae el rol del usuario del token JWT
    public String obtenerRol(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    //Verifica si el token JWT es valido
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
