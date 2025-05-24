package mt.mentalist.servicio.Funciones;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mt.mentalist.configuracion.API.Cie11Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cie11Servicio {
    @Autowired
    private Cie11Config cie11Config;
    @Autowired
    private RestTemplate restTemplate;

    //Metodo para obtener el token
    public String obtenerToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", cie11Config.getClientId());
        body.add("client_secret", cie11Config.getClientSecret());
        body.add("scope", cie11Config.getScope());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(cie11Config.getTokenUrl(), request, Map.class);
        if (response.getStatusCode() == HttpStatus.OK)
        {
            return (String) response.getBody().get("access_token");
        } else {
            throw new RuntimeException("Error al obtener el token: " + response.getStatusCode());
        }
    }

    //Metodo para buscar los diagnosticos por texto
    public List<Map<String, String>> buscarDiagnosticos(String texto){
        String token = obtenerToken();
        String url = cie11Config.getApiBase() + "/search?q=" + URLEncoder.encode(texto, StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        headers.set("API-Version", "v2");
        headers.set("Accept-Language", "es");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,entity, String.class);
        List<Map<String,String >> resultados = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode entidades = root.path("destinationEntities");
            entidades.forEach(nodo -> {
                Map<String,String> map = new HashMap<>();
                String codigo = nodo.path("theCode").asText();
                String tituloBase = nodo.path("title").asText();
                String tituloLimpio = tituloBase.replaceAll("<[^>]*>","");

                map.put("codigo", codigo);
                map.put("titulo", tituloLimpio);
                resultados.add(map);
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultados;

    }

}
