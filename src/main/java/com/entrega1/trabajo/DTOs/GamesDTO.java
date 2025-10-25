package com.entrega1.trabajo.DTOs;

import com.entrega1.trabajo.model.Game;

import java.time.LocalDate;

public class GamesDTO {
    
    private int id;
    private String name;
    private String rules;
    private String teams;
    private LocalDate dateStart;
    private RefereeDTO referee;

    public GamesDTO() {}

    public GamesDTO(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.rules = game.getRules();
        this.teams = game.getTeams();
        this.dateStart = game.getDateStart();
        
        if (game.getReferee() != null) {
            this.referee = new RefereeDTO(game.getReferee());
        }
    }


    public RefereeDTO getReferee() {
        return referee;
    }

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
