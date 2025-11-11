package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.DTOs.ApiDTO;
import com.entrega1.trabajo.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ApiDTO getWeather(@RequestParam(defaultValue = "Medellín") String city) {
        return weatherService.getWeather(city);
    }
}
