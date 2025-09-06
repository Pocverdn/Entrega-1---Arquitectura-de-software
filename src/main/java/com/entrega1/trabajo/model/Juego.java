package com.entrega1.trabajo.model; //Recuerden cambiar esto

import jakarta.persistence.*;

import java.util.*;
//import jakarta.validation.constraints.*;


@Entity
@Table(name = "juego")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotBlank(message = "El nombre es obligatorio")
    //@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    //@NotBlank(message = "Cuales son las reglas") Talvez se pueden dejar vacias?
    //@Size(min = 0, max = 1024, message = "El limite es de 1024 characteres")
    @Column(name = "reglas", nullable = false, length = 1024)
    private String reglas;

    //Las listas confunden a la base de datos entonces mejor tener un string grande
    //@Size(min = 0, max = 1024, message = "El limite es de 1024 characteres")
    @Column(name = "participantes", nullable = false, length = 1024)
    private String participantes;

    //@NotBlank(message = "la fecha es oligatoria")
    @Column(name = "fecha", nullable = false)
    private Date fecha;

}
