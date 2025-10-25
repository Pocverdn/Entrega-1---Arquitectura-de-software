package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.DTOs.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.entrega1.trabajo.model.Usuario;
import com.entrega1.trabajo.repository.UserRepository;
import com.entrega1.trabajo.service.JwtService;

@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "Registro Caba Pro",
    description = """
        ### Controlador para realizar operaciones REST

        Permite realizar las acciones de registro e inicio de sesión

        **Endpoints organizados por funcionalidad:**
        """

)
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    // Crear un nuevo usuario
    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Crea un nuevo usuario con nombre y contraseña"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado"),
        @ApiResponse(responseCode = "400", description = "Error al crear usuario"),
        @ApiResponse(responseCode = "409", description = "Conflicto, el usuario ya existe"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario (

        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuario y contraseña del referee",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RegisterForm.class),
                examples = {
                    @ExampleObject(
                    name = "Ejemplo de Registro Válido",
                    description = "Un ejemplo de cómo registrar un nuevo árbitro",
                    value = """
                        {
                          "username": "santiago",
                          "password": "123",
                          "confirmPassword": "123"
                        }
                    """
                    ),
                    @ExampleObject(
                        name = "Ejemplo de Registro 2",
                        description = "Otro ejemplo de registro",
                        value = """
                            {
                            "username": "pepe",
                            "password": "456",
                            "confirmPassword": "456"
                            }
                        """
                    )
                }
            )
        )
        @Valid @RequestBody RegisterForm registroForm

    ) {

        if (!registroForm.getPassword().equals(registroForm.getConfirmPassword())) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Datos Inválidos");
            errorResponse.put("message", "Las contraseñas no coinciden");

            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);

        }

        Usuario usuarioExistente = userRepository.findByUsername(registroForm.getUsername());

        if (usuarioExistente != null) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Conflicto");
            errorResponse.put("message", "El nombre de usuario ya existe");

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(errorResponse);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroForm.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroForm.getPassword()));
        usuario.setRole("ROLE_USER");
        userRepository.save(usuario);

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("id", usuario.getId());
        successResponse.put("username", usuario.getUsername());
        successResponse.put("role", usuario.getRole());
        successResponse.put("message", "Usuario registrado exitosamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(successResponse);
    }

    //Endpoint para el login
    @Operation(summary = "Autentica un usuario", description = "Valida las credenciales del usuario.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login exitoso"),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(

        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuario y contraseña del referee",
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
    
        @Valid @RequestBody LoginRequestDTO loginRequest
    ) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("status", "OK");
            response.put("message", "Login exitoso");
            response.put("username", authentication.getName());
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (BadCredentialsException e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "No Autorizado");
            errorResponse.put("message", "Credenciales inválidas");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(errorResponse);

        }


    }

}

