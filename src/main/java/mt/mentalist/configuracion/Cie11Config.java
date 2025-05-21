package mt.mentalist.configuracion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cie11")
@Data
public class Cie11Config {

    private String clientId;
    private String clienteSecret;
    private String tokenUrl;
    private String scope;
    private String apiBase;
}
