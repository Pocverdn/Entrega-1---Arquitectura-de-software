package com.entrega1.trabajo.DTOs;

import com.entrega1.trabajo.model.RefereeRequest;

public class RefereeRequestDTO {

    private int id;
    private String status;
    private RefereeDTO referee; // Usamos el DTO simple
    private GamesDTO game;
    
    public RefereeRequestDTO(RefereeRequest req) {
        this.id = req.getId();
        this.status = req.getStatus();
        
        if (req.getReferee() != null) {
            this.referee = new RefereeDTO(req.getReferee());
        }
        if (req.getGame() != null) {
            this.game = new GamesDTO(req.getGame());
        }
    }


    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getStatus() { 
        return status; 
    }

    public void setStatus(String status) { 
        this.status = status; 
    }

    public RefereeDTO getReferee() {
        return referee; 
    }

    public void setReferee(RefereeDTO referee) { 
        this.referee = referee; 
    }

    public GamesDTO getGame() { 
        return game; 
    }

    public void setGame(GamesDTO game) { 
        this.game = game; 
    }

}
