package com.entrega1.trabajo.service;

import com.entrega1.trabajo.DTOs.ApiDTO;
import org.springframework.stereotype.Service;

@Service
public class StaticWeatherProvider implements WeatherProvider {

    @Override
    public ApiDTO getWeather(String city) {
        ApiDTO dto = new ApiDTO();

        ApiDTO.Location loc = new ApiDTO.Location();
        loc.setName(city + " (simulado)");
        loc.setRegion("Región simulada");
        loc.setLocaltime("2025-11-11 12:00");

        ApiDTO.Current cur = new ApiDTO.Current();
        cur.setTemp_c(22.5);
        cur.setTemp_f(72.5);
        cur.setHumidity(60);

        dto.setLocation(loc);
        dto.setCurrent(cur);

        return dto;
    }
}
