package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.entrega1.trabajo.DTOs.ApiDTO;
import java.util.List;

@RestController
@RequestMapping("/clima")
@Tag(
    name = "clima API",
    description = """
        ### Servicio para obtener datos de clima

        **Endpoints organizados por funcionalidad:**
        """

)
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApiController {
    
    @Autowired
    private ApiService apiService;

    @Operation(summary = "Obtener clima actual por ciudad", description = "Consulta el clima actual usando una API externa.")
    @GetMapping("/actual")
    public ApiDTO getClimaActual(@RequestParam String ciudad) {
        return apiService.obtenerClima(ciudad);
    }

}
