package mt.mentalist.configuracion.Seguridad;


import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[JWT FILTER] No se encontró encabezado Authorization válido");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validarToken(token)) {
            System.out.println("[JWT FILTER] Token inválido o expirado");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
            return;
        }

        String usuario = jwtUtil.obtenerUsuario(token);
        String rol = jwtUtil.obtenerRol(token);
        String autoridadFinal = "ROLE_" + rol;

        System.out.println("=== JWT FILTER ===");
        System.out.println("TOKEN: " + token);
        System.out.println("USUARIO extraído: " + usuario);
        System.out.println("ROL extraído: " + rol);
        System.out.println("AUTHORITY construida: " + autoridadFinal);
        System.out.println("Antes de setAuthentication: " + SecurityContextHolder.getContext().getAuthentication());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        List.of(new SimpleGrantedAuthority(autoridadFinal))
                );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Después de setAuthentication: " + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("Authorities actuales en contexto: " + authentication.getAuthorities());

        filterChain.doFilter(request, response);
    }
}


