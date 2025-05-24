package mt.mentalist.configuracion.Seguridad;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    //Inyecta la clave secreta desde application.properties
    @Value("${jwt.secret}")
    private String secretKey;
    private static final long EXPIRATION_TIME=86400000;

    //Genera la clave secreta simetrica para firmar/verificar JWT usando HMAC-SHA
    private Key getKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //Genera un JWT con claims personalizados: usuario, rol e ID
    public String generarToken(String usuario, String rol, Integer idUsuario){
        return Jwts.builder()
                .setSubject(usuario) // Establece el usuario como 'subject' principal del token
                .claim("rol", rol) //Claim personalizado de rol
                .claim("id", idUsuario) // Claim personalizado del id del usuario
                .setIssuedAt(new Date()) //Fecha de emision del token
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) //Tiempo de expiracion de 24h
                .signWith(getKey(), SignatureAlgorithm.HS256) //Firma el token utilizando el algoritmo HMAC-SHA256
                .compact();
    }

    //Extrae el subject (nombre de usuario) del token JWT
    public String obtenerUsuario(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Extrae el rol del usuario del token JWT
    public String obtenerRol(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    //Verifica si el token JWT es valido
    public boolean validarToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
