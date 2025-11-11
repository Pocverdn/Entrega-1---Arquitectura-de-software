package com.entrega1.trabajo.service;

import com.entrega1.trabajo.DTOs.ApiDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiWeatherProvider implements WeatherProvider {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ApiDTO getWeather(String city) {
        try {
            String uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("key", apiKey)
                    .queryParam("q", city)
                    .queryParam("lang", "es")
                    .toUriString();

            return restTemplate.getForObject(uri, ApiDTO.class);
        } catch (RestClientException e) {
            // En caso de error devolvemos algo "seguro"
            ApiDTO errorResponse = new ApiDTO();

            ApiDTO.Location loc = new ApiDTO.Location();
            loc.setName("Desconocido");
            loc.setRegion("");
            loc.setLocaltime("");

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
