package com.entrega1.trabajo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrega1.trabajo.DTOs.RefereeRequestDTO;
import com.entrega1.trabajo.DTOs.RegisterForm;
import com.entrega1.trabajo.model.RefereeRequest;
import com.entrega1.trabajo.repository.RefereeRequestRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/requests")
@Tag(
    name = "Funciones para aceptar o rechazar partidos",
    description = """
        ### Controlador para realizar operaciones REST

        Permite al arbitro aceptar o rechazar partidos

        **Endpoints organizados por funcionalidad:**
        """

)
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class RefereeRequestRestController {

    @Autowired
    private RefereeRequestRepository requestRepository;


    @Operation(summary = "Aceptar un partido", description = "Acepta un partido el cual fue asignado al arbitro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Partido aceptado"),
        @ApiResponse(responseCode = "401", description = "Error al aceptar partido")
    })
    @PatchMapping("/{id}/accept")
    public ResponseEntity<?> accept(

        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Acepta un partido el cual fue asignado al arbitro",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RegisterForm.class),
                examples = {
                    @ExampleObject(
                    name = "Ejemplo de patido aceptado",
                    description = "Un ejemplo de cómo se acepta",
                    value = """
                        {
                            "username": "santiago",
                            "password": "123",
                        }
                    """
                    ),
                    @ExampleObject(
                        name = "Ejemplo de Registro 2",
                        description = "Otro ejemplo de autentificacion",
                        value = """
                            {
                            "username": "pepe",
                            "password": "456",
                            }
                        """
                    )
                }
            )
        )
    
        @PathVariable Integer id
    ) {

        Optional<RefereeRequest> optReq = requestRepository.findById(id);

        if (optReq.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Partido invalido");
            errorResponse.put("message", "No existe un partido con esa id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        RefereeRequest req = optReq.get();

        if (!req.getStatus().equals("pendiente")) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Partido ya contestado");
            errorResponse.put("message", "Ya aceptaste o rechazaste este partido");
            // 409 Conflict o 400 Bad Request son buenas opciones aquí
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }


        req.setStatus("aceptada");
        requestRepository.save(req);

        RefereeRequestDTO responseDTO = new RefereeRequestDTO(req);
        return ResponseEntity.ok(responseDTO);
    }

    
    @Operation(summary = "Rechazar un partido", description = "Rechaza un partido el cual fue asignado al arbitro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Partido Rechaza"),
        @ApiResponse(responseCode = "401", description = "Error al Rechazar partido")
    })
    @PatchMapping("/{id}/reject")
    public ResponseEntity<?> reject(

        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Rechaza un partido el cual fue asignado al arbitro",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RegisterForm.class),
                examples = {
                    @ExampleObject(
                    name = "Ejemplo de Registro Válido",
                    description = "Un ejemplo de cómo autentificar un árbitro",
                    value = """
                        {
                            "username": "santiago",
                            "password": "123",
                        }
                    """
                    ),
                    @ExampleObject(
                        name = "Ejemplo de Registro 2",
                        description = "Otro ejemplo de autentificacion",
                        value = """
                            {
                            "username": "pepe",
                            "password": "456",
                            }
                        """
                    )
                }
            )
        )
    
        @PathVariable Integer id
    ) {

        Optional<RefereeRequest> optReq = requestRepository.findById(id);

        if (optReq.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Partido invalido");
            errorResponse.put("message", "No existe un partido con esa id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        RefereeRequest req = optReq.get();

        if (!req.getStatus().equals("pendiente")) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Partido ya contestado");
            errorResponse.put("message", "Ya aceptaste o rechazaste este partido");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }


        req.setStatus("Rechazada");
        requestRepository.save(req);

        RefereeRequestDTO responseDTO = new RefereeRequestDTO(req);
        return ResponseEntity.ok(responseDTO);
    }
    
}
