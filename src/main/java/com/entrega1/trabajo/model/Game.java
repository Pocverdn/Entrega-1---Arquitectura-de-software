package com.entrega1.trabajo.model; //Recuerden cambiar esto

import jakarta.persistence.*;

import java.time.LocalDate;
//import jakarta.validation.constraints.*;


@Entity
@Table(name = "game")
public class Game {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   //@NotBlank(message = "El nombre es obligatorio")
   //@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
   @Column(name = "name", nullable = false, length = 100)
   private String name;

   //@NotBlank(message = "Cuales son las reglas") Talvez se pueden dejar vacias?
   //@Size(min = 0, max = 1024, message = "El limite es de 1024 characteres")
   @Column(name = "rules", nullable = false, length = 1024)
   private String rules;

   //Las listas confunden a la base de datos entonces mejor tener un string grande
   //@Size(min = 0, max = 1024, message = "El limite es de 1024 characteres")
   @Column(name = "teams", nullable = false, length = 1024)
   private String teams;

   //@NotBlank(message = "la fecha es oligatoria")
   @Column(name = "dateStart", nullable = false)
   private LocalDate dateStart;


   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTeams() {
      return teams;
   }

   public void setTeams(String teams) {
      this.teams = teams;
   } 

   public LocalDate getDateStart() {
      return dateStart;
   }

   public void setDateStart (LocalDate dateStart) {
      this.dateStart = dateStart;
   }  

   public String getRules() {
      return rules;
   }

   public void setrules(String rules) {
      this.rules = rules;
   } 

}
