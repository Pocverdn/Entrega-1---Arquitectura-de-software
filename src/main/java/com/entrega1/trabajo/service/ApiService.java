package com.entrega1.trabajo.service;

import com.entrega1.trabajo.DTOs.ApiDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
    
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;


    private final RestTemplate restTemplate = new RestTemplate();

    public ApiDTO obtenerClima(String ciudad) {
        try {
            // Construir la URL completa
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("key", apiKey)
                    .queryParam("q", ciudad)
                    .toUriString();

            // Llamar a la API
            return restTemplate.getForObject(url, ApiDTO.class);

        } catch (RestClientException e) {

            System.out.println("Error al consultar la API del clima: " + e.getMessage());
            // Puedes loguear el error o devolver un objeto vacío
            ApiDTO errorResponse = new ApiDTO();

            ApiDTO.Location loc = new ApiDTO.Location();
            loc.setName(ciudad);
            loc.setRegion("Desconocido");
            loc.setLocaltime("N/A");

            ApiDTO.Current cur = new ApiDTO.Current();
            cur.setTemp_c(0);
            cur.setTemp_f(0);
            cur.setHumidity(0);

            errorResponse.setLocation(loc);
            errorResponse.setCurrent(cur);

            return errorResponse;
        }
    }

}
