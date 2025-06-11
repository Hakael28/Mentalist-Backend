package mt.mentalist.configuracion.API;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI mentalistAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Mentalist")
                        .version("1.0")
                        .description("Documentacion de la API para el sistema Mentalist"));

    }
}
