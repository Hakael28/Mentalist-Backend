package mt.mentalist.Funciones.Seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ConfiguracionSeguridad {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Configuracion del filtro de seguridad HTTP principal
        return httpSecurity
                .csrf(csrf -> csrf.disable())//Desactiva CSRF (falsificación de petición en sitios cruzados) por que se esta utilizando JWT y no cookies
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))//Habilita el CORS para permitir el acceso desde otros origines
                .authorizeHttpRequests(auth -> auth //Define que rutas requieren autenticacion
                        .requestMatchers("/auth/**").permitAll()//Acceso publico
                        .requestMatchers("/cie11/**").authenticated()
                        .requestMatchers("/mentalist-web/**").authenticated()//Requiere autenticacion
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/v3/api-docs/swagger-config"
                        ).permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//No se guarda sesion en el servidor
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)//Agrega filtro JWT antes del filtro de autenticacion estandar
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        //Configuracion de la reglas CORS permitiendo origenes especificos
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(allowedOrigins));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
