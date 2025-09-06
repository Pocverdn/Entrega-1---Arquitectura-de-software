package com.entrega1.trabajo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;

import com.entrega1.trabajo.DTOs.RegisterForm;
import com.entrega1.trabajo.model.Usuario;
import com.entrega1.trabajo.repository.UserRepository;

@Controller
public class RegisterController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("registro", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute("registro") RegisterForm registroForm, BindingResult result,
            Model model) {

        // Validar que las contraseñas coincidan
        if (!registroForm.getPassword().equals(registroForm.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.registro", "Las contraseñas no coinciden");
        }

        // Verificar si el usuario ya existe
        Usuario usuarioExistente = userRepository.findByUsername(registroForm.getUsername());
        if (usuarioExistente != null) {
            result.rejectValue("username", "error.registro", "El usuario ya existe");
        }

        // Si hay errores, se regresa al formulario
        if (result.hasErrors()) {
            return "register";
        }

        // Crear y guardar el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registroForm.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroForm.getPassword()));
        usuario.setRole("ROLE_USER");
        userRepository.save(usuario);

        return "redirect:/login?registroExitoso";
    }

}
