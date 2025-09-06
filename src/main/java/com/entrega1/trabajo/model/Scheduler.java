package com.entrega1.trabajo.model;//Recuerden cambiar esto

import jakarta.persistence.*;

import java.util.*;



@Entity
@Table(name = "scheduler")
public class Scheduler {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @OneToMany(mappedBy = "shceduler", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tournament> torneos= new ArrayList<>();


   @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Juego> juegos= new ArrayList<>();

}

