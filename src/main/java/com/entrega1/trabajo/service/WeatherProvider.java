package com.entrega1.trabajo.service;

import com.entrega1.trabajo.DTOs.ApiDTO;

public interface WeatherProvider {

    
    ApiDTO getWeather(String city);
}
