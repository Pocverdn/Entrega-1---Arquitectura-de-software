package com.entrega1.trabajo.DTOs;

import com.entrega1.trabajo.model.Referee;

public class RefereeDTO {
    private int id;
    private String username;

    public RefereeDTO() {}

    public RefereeDTO(Referee referee) {
        this.id = referee.getId();
        this.username = referee.getName();
    }

    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getUsername() { 
        return username; 
    }

    public void setUsername(String username) { 
        this.username = username;
    }

}
