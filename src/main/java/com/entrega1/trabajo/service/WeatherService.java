package com.entrega1.trabajo.service;

import com.entrega1.trabajo.DTOs.ApiDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherProvider weatherProvider;

    // Aquí elegimos cuál implementación inyectar.
    // Usa "apiWeatherProvider" para la API real
    // o "staticWeatherProvider" para datos quemados.
    public WeatherService(@Qualifier("apiWeatherProvider") WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public ApiDTO getWeather(String city) {
        return weatherProvider.getWeather(city);
    }
}
