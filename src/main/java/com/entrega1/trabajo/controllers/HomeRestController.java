package com.entrega1.trabajo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrega1.trabajo.DTOs.GamesDTO;
import com.entrega1.trabajo.DTOs.homeDTO;
import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.model.RefereeRequest;
import com.entrega1.trabajo.service.UserDetailsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/home")
@Tag(
    name = "Inicio de Caba Pro",
    description = """
        ### Controlador para realizar operaciones REST

        Permite visualizar mas información sobre el usuario (nombre, liga, especialidad)

        **Endpoints organizados por funcionalidad:**
        """

)
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class HomeRestController {
    
    @Autowired
    private com.entrega1.trabajo.repository.RefereeRequestRepository refereeRequestRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Operation(
        summary = "Consultar el home del arbitro",
        description = "Muestra la información del referee (nombre, liga, especialidad)"
    )
    @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Información del home obtenida exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiResponse.class),
            examples = {
                @ExampleObject(
                    name = "Referee Santiago",
                    description = "Referee cualquiera",
                    value = """
                        {
                            "special": "Fifa",
                            "success": "true",
                            "league": "Europa",
                            "name": "santiago",
                            "Id": "34",
                            "statusCode": "200"
                        }
                        """
                ),
                @ExampleObject(
                    name = "Referee Alex",
                    description = "Referee cualquiera",
                    value = """
                        {
                            "special": "Uefa",
                            "success": "true",
                            "league": "Colombia",
                            "name": "Alex",
                            "Id": "33",
                            "statusCode": "200"
                        }
                        """
                )
            }
        )
    ),
    @ApiResponse(responseCode = "400", description = "Error al pedir la información"),
    @ApiResponse(responseCode = "404", description = "El usuario autenticado no tiene un perfil de árbitro"),
    @ApiResponse(responseCode = "500", description = "Error por parte del servidor")
    })
    @GetMapping("/")
    public ResponseEntity<?> getRefereeDashboard(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Referee referee = userDetailsService.getRefereeByUsername(username);

        if (referee == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", "No existe un perfil de árbitro para el usuario: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        List<RefereeRequest> requests = refereeRequestRepository.findByRefereeId(referee.getId());

        homeDTO homePage = new homeDTO(referee, requests);

        Map<String, String> response = new HashMap<>();
        response.put("league",  homePage.getRefereeInfo().getLeague());
        response.put("special", homePage.getRefereeInfo().getSpecial());
        response.put("name", homePage.getRefereeInfo().getName());
        response.put("Id", String.valueOf(homePage.getRefereeInfo().getId()));
        response.put("success", "true");
        response.put("statusCode", "200");
        
        

        return ResponseEntity.ok(response);

    }
    

    @Operation(
        summary = "Consultar el juegos del arbitro",
        description = "Muestra los juegos asignados del referee"
    )
    @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Información del home obtenida exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiResponse.class),
            examples = {
                @ExampleObject(
                    name = "Referee Santiago",
                    description = "Referee cualquiera",
                    value = """
                        {
                            "id": 79,
                            "name": "Juego 6",
                            "rules": "Rule 6",
                            "teams": "Nacional, Cali",
                            "dateStart": "2025-09-20",
                            "referee": {
                                "id": 34,
                                "username": "santiago"
                            }
                        }
                        """
                ),
                @ExampleObject(
                    name = "Referee Alex",
                    description = "Referee cualquiera",
                    value = """
                        {
                            "id": 80,
                            "name": "Juego 7",
                            "rules": "Rule 7",
                            "teams": "Medellin, Millonarios",
                            "dateStart": "2025-10-20",
                            "referee": {
                                "id": 35,
                                "username": "isabel"
                            }
                        }
                        """
                )
            }
        )
    ),
    @ApiResponse(responseCode = "400", description = "Error al pedir la información"),
    @ApiResponse(responseCode = "404", description = "El usuario autenticado no tiene un perfil de árbitro"),
    @ApiResponse(responseCode = "500", description = "Error por parte del servidor")
    })
    @GetMapping("/games")
    public ResponseEntity<?> getRefereeGames(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Referee referee = userDetailsService.getRefereeByUsername(username);

        if (referee == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", "No existe un perfil de árbitro para el usuario: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        List<Game> games = referee.getGames();

        List<GamesDTO> gameDTOs = new ArrayList<>();
        for (Game game : games) {
            gameDTOs.add(new GamesDTO(game));
        }
        
        

        return ResponseEntity.ok(gameDTOs);

    }

}
